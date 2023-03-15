package com.example.profiling.events;

import jdk.jfr.Category;
import jdk.jfr.DataAmount;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.Timestamp;

@Name(GreetingEvent.NAME)
@Label("Greeting")
@Category({"Demo", "Profiling"})
@Description("Greeting a person")
public class GreetingEvent extends Event {

    public static final String NAME = "profiling.events.GreetingEvent";

    @Label("Person Name")
    public String name;

    @Label("Invoke Date")
    @Timestamp
    public long invokeDate;

    @Label("Name Size")
    @DataAmount
    public long size;

    @Label("Class Value")
    Class<?> classValue;


}
