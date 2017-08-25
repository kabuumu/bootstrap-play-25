/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.play.bootstrap.http

import javax.inject.{Inject, Singleton}

import play.api.Configuration
import uk.gov.hmrc.http._
import uk.gov.hmrc.play.audit.http.HttpAuditing
import uk.gov.hmrc.play.audit.http.connector.AuditConnector
import uk.gov.hmrc.play.http.ws._

@Singleton
class DefaultHttpClient @Inject() (
                                    config: Configuration,
                                    override val auditConnector: AuditConnector
                                  )
  extends WSGet with HttpGet
    with WSPut with HttpPut
    with WSPost with HttpPost
    with WSDelete with HttpDelete
    with WSPatch with HttpPatch
    with HttpAuditing {

  // TODO extract into its own class
  override val appName: String = config.getString("app.name").getOrElse("NO APP NAME SET")

  override val hooks = Seq(AuditingHook)
}