package com.rockthejvm.scalabeginners.lectures.part2

object Enumerations extends App {


  enum Permissions {
    case READ, READ_WRITE, WRITE, EXECUTE, ALL, NONE
  }

  val executePermissions: Set[Permissions] = Set(Permissions.EXECUTE, Permissions.ALL)

  def execute(permission: Permissions) =
    if(executePermissions.contains(permission))
      println("executing")
    else
      println("permission denied")

  execute(Permissions.READ)
  execute(Permissions.ALL)
  execute(Permissions.NONE)


  //Enum with parameters
  enum PermissionsWithBits(bits: Int) {
    case NONE extends PermissionsWithBits(0)        // 000
    case READ extends PermissionsWithBits(1)        // 001
    case WRITE extends PermissionsWithBits(2)       // 010
    case EXECUTE extends PermissionsWithBits(4)     // 100
    case READ_WRITE extends PermissionsWithBits(3)  // 011
    case ALL extends PermissionsWithBits(7)         // 111
  }

  // Companion object for Enum
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = ???
  }

  //ordinal to fetch the position of the enum in the index
  println(PermissionsWithBits.READ.ordinal)
  println(PermissionsWithBits.ALL.ordinal)


  //fetch all values for the enum
  PermissionsWithBits.values foreach println

  //fetch enum from string name
  val permission = PermissionsWithBits.valueOf("READ_WRITE")
  println(permission)
}
