package com.yousefagawin.codingchallengeapp.util

import java.io.IOException

//this file will handle all the exceptions
class ApiException(message: String): IOException(message)

class NoInternetException(message: String): IOException(message)