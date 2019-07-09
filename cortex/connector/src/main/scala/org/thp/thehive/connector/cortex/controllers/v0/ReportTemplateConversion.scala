package org.thp.thehive.connector.cortex.controllers.v0

import io.scalaland.chimney.dsl._
import org.thp.cortex.dto.v0.OutputReportTemplate
import org.thp.scalligraph.Output
import org.thp.scalligraph.models.Entity
import org.thp.scalligraph.query.{PublicProperty, PublicPropertyListBuilder}
import org.thp.thehive.connector.cortex.models.ReportTemplate
import org.thp.thehive.connector.cortex.services.JobSteps

import scala.language.implicitConversions

object ReportTemplateConversion {

  implicit def toOutputReportTemplate(j: ReportTemplate with Entity): Output[OutputReportTemplate] =
    Output[OutputReportTemplate](
      j.into[OutputReportTemplate]
        .withFieldComputed(_.analyzerId, _.workerId)
        .withFieldComputed(_.id, _._id)
        .withFieldComputed(_.content, _.content)
        .withFieldComputed(_.reportType, _.reportType.toString)
        .transform
    )

  val reportTemplateProperties: List[PublicProperty[_, _]] =
    PublicPropertyListBuilder[JobSteps]
      .property[String]("analyzerId")(_.rename("workerId").updatable)
      .property[String]("id")(_.simple.readonly)
      .property[String]("content")(_.simple.updatable)
      .property[String]("reportType")(_.simple.updatable)
      .build
}