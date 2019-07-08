package org.thp.thehive.connector.cortex.controllers.v0

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

import javax.inject.{Inject, Singleton}

@Singleton
class Router @Inject()(jobCtrl: JobCtrl, analyzerCtrl: AnalyzerCtrl, actionCtrl: ActionCtrl, cortexQueryExecutor: CortexQueryExecutor)
    extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/job/$jobId<[^/]*>") => jobCtrl.get(jobId)
    case POST(p"/job/_search")      => cortexQueryExecutor.job.search
    case POST(p"/job")              => jobCtrl.create

    case GET(p"/analyzer")        => analyzerCtrl.list
    case POST(p"/action/_search") => actionCtrl.list
  }
}
