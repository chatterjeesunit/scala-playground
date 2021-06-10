import java.sql.Timestamp

import com.wealthminder.common.data.dto.SurveyResponseInputDTO
import com.wealthminder.marketplace.data.dto.advisor.AdAdvisorInfo
import com.wealthminder.marketplace.data.dto.{ContactInfo, QuoteProposalAttachment, QuoteProposalStatus}
import org.json4s.DefaultFormats
import org.json4s.ext.EnumNameSerializer
import org.json4s.native.JsonMethods._


//implicit val formats = DefaultFormats + new EnumNameSerializer (QuoteProposalStatus)

case class QuoteProposalDTO2 (
  proposalId : Long = -1,
//  guid : String = null,
//  detail: String = null,
//  createdOn : Timestamp = null,
//  viewedByClient : Boolean = false,
//  lastViewedOn : Option[Timestamp] = None,
//  introductionDone : Boolean = false,
  introducedOn : Option[Timestamp] = None,
//  advisorInfo : AdAdvisorInfo = null,
//  clientContactInfo: Option[ContactInfo] = None,
//  messageCount: Int = -1,
//  hasUnreadMessages: Boolean = false,
//  proposalAccepted:Boolean = false,
//  attachments : Option[List[QuoteProposalAttachment]] = None,
  status : QuoteProposalStatus.Value = QuoteProposalStatus.OPEN,
//  resendIntroduction:Option[Boolean] = None
  surveyResponse : Option[SurveyResponseInputDTO] = None
                             )


val lines = scala.io.Source.fromFile("/datadrive/WealthMinder/Work/marketplace/M14/proposalDTO.txt").getLines.toList
val json = parse(lines.head)

implicit val formats = DefaultFormats + new EnumNameSerializer (QuoteProposalStatus)
val result = json.extract[QuoteProposalDTO2]

result