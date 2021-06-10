package com.hackerrank.practice.algorithms.graphtheory.medium

import scala.collection.mutable

/**
  * Created by sunitc on 10/30/17.
  * https://www.hackerrank.com/challenges/torque-and-development/problem
  */

/*
Sample Input
2
3 3 2 1
1 2
3 1
2 3
6 6 2 5
1 3
3 4
2 4
1 2
2 3
5 6

Sample Output
4
12


Sample Input
1
9 2 91 84
8 2
2 9
 */
object RoadsLibraries {

  def main(args: Array[String]): Unit = {
    val sc: java.util.Scanner = new java.util.Scanner(System.in)
    val q:Int = sc.nextInt()

    (1 to q) foreach (testcase => {
      val cities: Int = sc.nextInt
      val roads: Int = sc.nextInt
      val libCost: Long = sc.nextInt
      val roadCost: Long = sc.nextInt
      val graph:Graph = new Graph(cities, roads)
      (1 to roads) foreach(r => {
        val c1: Int = sc.nextInt
        val c2: Int = sc.nextInt
        graph.addEdge(c1, c2)
      })
      println(calculateCost(graph, libCost, roadCost))
    })

  }

  def calculateCost(graph: Graph, libCost: Long, roadCost: Long): Long = {
    if(roadCost >= libCost) graph.numVertex * libCost
    else {
      var marked:Array[Boolean] = new Array[Boolean](graph.numVertex)
      var maxHeap: MaxHeap = new MaxHeap(graph.numVertex)
      val allEdges: Map[Int, Int] = graph.allEdgesSize()
      (0 until graph.numVertex) foreach(i =>{
        val neighbours: Int = allEdges.getOrElse(i, 0)
        if(neighbours > 0) maxHeap.add(VEC(i, neighbours ))
        marked.update(i, false)
      })
//      println(maxHeap)
//      println(graph)

      var librariesCount: Long = 0l
      var pathCount: Long = 0l
      while(!maxHeap.isEmpty()) {
        val vertex: VEC = maxHeap.deleteMax()
//        println(vertex + " -> " + graph.neighbours(vertex.vertex))
        if(!marked(vertex.vertex)) {
          librariesCount += 1
          marked.update(vertex.vertex, true)
          graph.neighbours(vertex.vertex).foreach(neighbour => {
            if(!marked(neighbour)) {
              marked.update(neighbour, true)
              pathCount += 1
            }
          })
        }
      }
      librariesCount += marked.filter(m => !m).size
//      println(librariesCount + ":" + pathCount)
      pathCount*roadCost + librariesCount*libCost
    }

  }


  case class Graph(numVertex: Int, numEdges: Int) {
    private var vertexEdges: mutable.Map[Int, mutable.MutableList[Int]] = mutable.HashMap[Int, mutable.MutableList[Int]]()


//    (0 until numVertex).foreach(v => {
//      vertexEdges.put(v, new mutable.MutableList[Int])
//    })

    def addEdge(v1:Int, v2:Int):Unit = {
      val list1: mutable.MutableList[Int] = vertexEdges.getOrElse(v1-1, new mutable.MutableList[Int])
      val list2: mutable.MutableList[Int] = vertexEdges.getOrElse(v2-1, new mutable.MutableList[Int])
      list1 += v2-1
      list2 += v1-1
      vertexEdges.put(v1-1, list1)
      vertexEdges.put(v2-1, list2)
    }

    def allEdgesSize():Map[Int, Int] = vertexEdges.map(m => (m._1, m._2.toList.size)).toMap

    def neighbours(v: Int): List[Int] = vertexEdges.map(m => (m._1, m._2.toList)).toMap.getOrElse(v, List[Int]())

  }

  case class VEC(val vertex:Int, val numEdges: Int) {
    def compareTo(other: VEC): Int = {
      this.numEdges.compare(other.numEdges)
    }
  }

  case class MaxHeap(size: Int) {
    var heapArray: Array[VEC] = new Array[VEC](size)

    override def toString: String = heapArray.mkString(" ; ")
    var last: Int = -1

    def isEmpty(): Boolean = last < 0

    def isFull(): Boolean = last == size - 1

    def add(v: VEC): Unit = {
      if(isFull()) throw new IndexOutOfBoundsException("Heap is full")
      last += 1
      heapArray.update(last, v)
      if(last > 0) {
        moveUp(last)
      }

    }


    def deleteMax(): VEC = {
      if(isEmpty()) throw new IndexOutOfBoundsException("Heap is empty")
      val max: VEC = heapArray(0)
      swap(0, last)
      heapArray.update(last, null)
      last = last - 1
      moveDown(0)
      max
    }

    private def moveDown(pos: Int):Unit = {
      var child: Int = 2*pos

      //check if first child are within limits
      if(child <= last) {
        //check if second child exists and if it is lower than first child
        if(child + 1 <= last && heapArray(child+1).compareTo(heapArray(child)) > 0) {
          child = child + 1
        }

        if(heapArray(child).compareTo(heapArray(pos)) > 0) {
          //move the item down
          swap (pos, child)
          moveDown(child)
        }
      }


    }

    private def moveUp(pos: Int): Unit = {
      var parent: Int = pos / 2
      if(heapArray(pos).compareTo(heapArray(parent))>0) {
        swap(pos, parent)
        if(parent > 0){
          moveUp(parent)
        }
      }

    }

    def swap(i:Int, j:Int):Unit = {
      val temp: VEC = heapArray(i)
      heapArray.update(i, heapArray(j))
      heapArray.update(j, temp)
    }

  }
}