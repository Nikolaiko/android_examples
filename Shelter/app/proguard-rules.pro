-dontwarn javax.inject.**
-dontwarn javax.annotation.**

-keep class javax.inject.**
-keep class javax.annotation.**
-keep class * implements toothpick.Factory

-keepclassmembers class * {
	@javax.inject.Inject <init>(...);
	@javax.inject.Inject <init>();
	@javax.inject.Inject <fields>;
	@javax.inject.Inject <methods>;
	public <init>(...);
}

-keepnames class * {
    @javax.inject.Inject <init>();
}