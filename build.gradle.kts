plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //testCompile("junit", "junit", "4.12")
    testImplementation( "io.cucumber:cucumber-junit:6.10.3")
    testImplementation( "io.cucumber:cucumber-java:6.10.3")

}

/*tasks.getByName<Test>("test") {
    //useJUnitPlatform()
    useTestNG()
}*/
