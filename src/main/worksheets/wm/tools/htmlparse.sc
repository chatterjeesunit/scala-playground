import com.wealthminder.marketplace.util.MarketPlaceUtil
import org.apache.commons.lang.StringEscapeUtils

import scala.util.matching.Regex
val str1:String = "<p>Martin Hopkins (CRD# 3021373) is an <strong>Investment Advisor Representative</strong> working at Hopkins Investment Management, LLC in Annapolis, MD and has <strong>over 17 years of experience</strong> in the finance industry. Martin Hopkins has taken additional exams to become a <strong>Certified Financial Planner (CFP&reg;)</strong>. CFP professionals must pass the comprehensive CFP Certification Examination,pass CFP Board&#39;s Fitness Standards for Candidates and Registrants, agree to abide by CFP Board&#39;s Code of Ethics and Professional Responsibility and Rules of Conduct which putclients&#39; interests first and comply with the Financial Planning Practice Standards.</p>"
val str2:String = "Vyacheslav Kuzmin (CRD# 5809578) is an <b>Investment Advisor Representative</b> working at Hsbc Securities (usa) Inc. in New York, NY and has <b>over 5 years of experience</b> in the finance industry. Vyacheslav Kuzmin has taken additional exams to become a <b>Certified Financial Planner (CFP®)</b>. CFP professionals must pass the comprehensive CFP Certification Examination,pass CFP Board's Fitness Standards for Candidates and Registrants, agree to abide by CFP Board's Code of Ethics and Professional Responsibility and Rules of Conduct which putclients' interests first and comply with the Financial Planning Practice Standards."
val str3:String = "<p>Michael Bell (CRD# 2420219) is an <strong>Investment Advisor Representative</strong> working at Next Financial Group, Inc. in Tallahassee, FL and has <strong>over 21 years of experience</strong> in the finance industry. Michael Bell has taken additional exams to become a <strong>Certified Financial Planner (CFP&reg;)</strong>. CFP yes.</p>"
val str4:String = "<p><!--[if gte vml 1]><v:shapetype\n id=\"_x0000_t75\" coordsize=\"21600,21600\" o:spt=\"75\" o:preferrelative=\"t\"\n path=\"m@4@5l@4@11@9@11@9@5xe\" filled=\"f\" stroked=\"f\">\n <v:stroke joinstyle=\"miter\"/>\n <v:formulas>\n  <v:f eqn=\"if lineDrawn pixelLineWidth 0\"/>\n  <v:f eqn=\"sum @0 1 0\"/>\n  <v:f eqn=\"sum 0 0 @1\"/>\n  <v:f eqn=\"prod @2 1 2\"/>\n  <v:f eqn=\"prod @3 21600 pixelWidth\"/>\n  <v:f eqn=\"prod @3 21600 pixelHeight\"/>\n  <v:f eqn=\"sum @0 0 1\"/>\n  <v:f eqn=\"prod @6 1 2\"/>\n  <v:f eqn=\"prod @7 21600 pixelWidth\"/>\n  <v:f eqn=\"sum @8 21600 0\"/>\n  <v:f eqn=\"prod @7 21600 pixelHeight\"/>\n  <v:f eqn=\"sum @10 21600 0\"/>\n </v:formulas>\n <v:path o:extrusionok=\"f\" gradientshapeok=\"t\" o:connecttype=\"rect\"/>\n <o:lock v:ext=\"edit\" aspectratio=\"t\"/>\n</v:shapetype><v:shape id=\"Picture_x0020_1\" o:spid=\"_x0000_s1026\" type=\"#_x0000_t75\"\n alt=\"Brian Kuhn Headshot Soft Edge\" style='position:absolute;left:0;\n text-align:left;margin-left:0;margin-top:0;width:102pt;height:102pt;z-index:251658240;\n visibility:visible;mso-wrap-style:square;mso-width-percent:0;\n mso-height-percent:0;mso-wrap-distance-left:9pt;mso-wrap-distance-top:0;\n mso-wrap-distance-right:9pt;mso-wrap-distance-bottom:0;\n mso-position-horizontal:absolute;mso-position-horizontal-relative:text;\n mso-position-vertical:absolute;mso-position-vertical-relative:text;\n mso-width-percent:0;mso-height-percent:0;mso-width-relative:margin;\n mso-height-relative:margin'>\n <v:imagedata src=\"file:///C:\\Users\\psgbpk\\AppData\\Local\\Temp\\msohtmlclip1\\01\\clip_image001.jpg\"\n  o:title=\"Brian Kuhn Headshot Soft Edge\"/>\n <w:wrap type=\"square\"/>\n</v:shape><![endif]-->Brian serves as the manager of a division within Planning Solutions Group; a Fulton MD hybrid RIA with over $500 million in assets under management.&nbsp; The division he manages, called PSG Clarity, is a unique planning team that focuses on affordable &amp; efficient financial planning for hard working Americans who have been under served by the investment management industry thus far.&nbsp; Generally focusing on individuals&nbsp;<em>under&nbsp;</em>a certain threshold of investable assets, rather than&nbsp;<em>above</em>&nbsp;it, Brian &amp; his team help everyday folks accomplish financial goals that have thus far proved difficult due to complexity, procrastination, or being overlooked by other planners.</p><p>In his 14th year as of January in the financial planning field, Brian focuses his practice in the areas of retirement planning, investments, &amp; insurance protection, with a special interest in assisting public sector employees. He is the author of &ldquo;Total Compensation: A Practical Guide to Federal Employee Benefits&rdquo; which is available on Amazon.com &amp; Amazon Kindle. In 2015 he will also be releasing the book &ldquo;The Personal Finance Handbook, a Guide to the Most Common Personal Finance Questions&rdquo;.&nbsp; He has contributed articles or been quoted on personal finance by the WSJ, USNews.com, Yahoo Finance, HuffingtonPost, &amp; Fedsmith.com among others.&nbsp; He has appeared in financial planning news articles at What&rsquo;s Up Annapolis magazine, Taste of the Bay magazine, The Severna Park Voice, &amp; on Michael Hodes&rsquo; Estate &amp; Chris Hensley&rsquo;s Retirement Planning radio shows, as well as on ABC News TV Affiliate WMAR in Baltimore.</p><p>He is the host of the bi-weekly TV show &ldquo;Your Future Your Finances which airs on channel 16 MMC in Montgomery County MD.&nbsp; Brian currently serves as a board member for the Baltimore chapter of the Society of Financial Service Professionals, is a member of the Maryland Financial Planning Association &amp; participates on their PR committee, &amp; is on the marketing committee of the West County Chamber of Commerce.</p><p>His profile as a financial planner is featured at wiseradvisor.com, the CFP board&rsquo;s letsmakeaplan.org site, nerdwallet.com, Guidevine.com, SaplingAdvisory.com &amp; Brightscope.com.&nbsp; He has been interviewed as a featured author at Businessinfoguide.com.</p><p>Through the FPA&rsquo;s speaker&rsquo;s bureau he spoke in 2014 on federal employee benefits to the organization&rsquo;s members &amp; also at the Library of Congress for federal employees. He was named a 5 Star Wealth Manager for 2015.</p><p>He is a CERTIFIED FINANCIAL PLANNER&trade;&nbsp;(CFP&reg;), Chartered Life Underwriter (CLU&reg;), &amp; Certified in Long Term Care (CLTC). He holds his FINRA Series 7, &amp; 66 registrations.&nbsp; Brian graduated from Towson University with a B.S. in Business Administration &amp; a minor in English.&nbsp; He lives with his wife Merin &amp; their daughters Charlotte &amp; Caroline in Odenton, MD.</p><p>Maryland Office 8161 Maple Lawn Blvd, Suite 400, Fulton, MD 20759 &bull; Phone 301.543.6035 &bull; Fax 301.543.6030&nbsp;&bull; Bkuhn@PSGClarity.com</p><p>- See more at: http://psgclarity.com</p><p>&nbsp;</p><p>Securities offered through Triad Advisors, Member FINRA / SIPC.&nbsp; Advisory Services offered through Planning Solutions Group, LLC.&nbsp; Planning Solutions Group, LLC is not affiliated with Triad Advisors. PSG Clarity is a division of Planning Solutions Group, LLC</p>"
val OPENING_TAG:Regex = """<(.*?)>""".r
val CLOSING_TAG:Regex = """</(.*?)>""".r
val SELF_CLOSING_TAG:Regex = """<(.*?)/>""".r
val COMMENT_TAG:Regex = """<!(.*?)-->""".r
val TOKEN_DELIM:String = "\n"
val ANY_HTML_TAG:String = "<.*?>"
def createTokenList(input:String):List[String]= input match {
  case COMMENT_TAG(_*) => List(input)
  case _ => StringEscapeUtils
    .unescapeHtml(input)
    .replace(">", s">$TOKEN_DELIM")
    .replace("<",s"$TOKEN_DELIM<")
    .split(TOKEN_DELIM)
    .toList
}
def createTokenListEscapingHtmlComment(input:String):List[String] =
  input
    .replaceAll("\n", " ")
    .replaceAll("<!--",s"$TOKEN_DELIM<!--")
    .replaceAll("-->",s"-->$TOKEN_DELIM")
    .split(TOKEN_DELIM)
    .map(createTokenList(_))
    .flatten
    .toList
def createClosingTag(openingTag:String):String =
  openingTag.substring(0,1) + "/" + openingTag.substring(1)
def truncateHtml(inputHtml:String, characterLimit:Int):String = {
  val nonHtmlTextLength:Int = inputHtml.replaceAll("\n", "").replaceAll(COMMENT_TAG.toString, "").replaceAll(ANY_HTML_TAG, "").length
  if (nonHtmlTextLength <= characterLimit)
    inputHtml
  else {
    var charLimit:Int = characterLimit
    var openHTMLTags:collection.mutable.MutableList[String] = new collection.mutable.MutableList[String]
    val truncatedString:String = createTokenListEscapingHtmlComment(inputHtml).map { str =>
      charLimit match {
        case len if len > 0 => {
          str match {
            case TOKEN_DELIM => ""
            case COMMENT_TAG(_*) | SELF_CLOSING_TAG(_*) => str
            case CLOSING_TAG(_*) => { //Closing Tags
            val lastTag = createClosingTag(openHTMLTags.last)
              if(lastTag.equals(str)) openHTMLTags = openHTMLTags.dropRight(1)
              str
            }
            case OPENING_TAG(_*) => { //Opening Tags
              openHTMLTags += str
              str
            }
            case _ => {
              charLimit = charLimit - str.length
              if(charLimit <= 0) {
                //E.g if char limit is -10 and
                //str is -> wild card entry for US Open
                //str.length is -> 27
                // This means we can keep around 17 chars (27 + (-10)) = 17
                // So find the next space after 17 position and keep text till that.
                val textToTake:Int = str.indexOf(" ", str.length + charLimit)
                str.substring(0, textToTake) + "..."
              }
              else
                str
            }
          }
        }
        case _ => "" //Return Empty Strings
      }
    }.mkString("")
    val remainingClosingTags:String = openHTMLTags.toList.reverseMap(createClosingTag(_)).mkString("")
    truncatedString + remainingClosingTags
  }
}
truncateHtml(str1, 600)
truncateHtml(str2, 600)
truncateHtml(str3, 600)
truncateHtml(str4, 600)
MarketPlaceUtil.truncateHtml(str4, 600)


MarketPlaceUtil.truncateHtml()