package com.recruitment.rest;

import com.recruitment.rest.model.User;

public class TestHelper {
  public static User getUserExample (){
      return new User(583231L, "octocat", "The Octocat", "User",
              "https://avatars.githubusercontent.com/u/583231?v=4", 10776,
              8, "2011-01-25T18:44:36Z");
  }

}
