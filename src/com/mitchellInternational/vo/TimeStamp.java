package com.mitchellInternational.vo;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimeStamp extends XmlAdapter<Date, Timestamp> {
	      @Override
	      public Date marshal(Timestamp v) {
	          return new Date(v.getTime());
	      }
	      @Override
	      public Timestamp unmarshal(Date v) {
	          return new Timestamp(v.getTime());
	      }
	  }