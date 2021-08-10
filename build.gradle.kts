buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(AppDependencies.gradle)
        classpath(AppDependencies.kotlin)
        classpath(AppDependencies.hiltPlugin)
        classpath(AppDependencies.navigationSafeArgs)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
