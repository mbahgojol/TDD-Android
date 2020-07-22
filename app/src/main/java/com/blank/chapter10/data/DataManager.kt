package com.blank.chapter10.data

import com.blank.chapter10.data.local.db.DbHelper
import com.blank.chapter10.data.remote.ApiHelper

interface DataManager : ApiHelper, DbHelper {

}