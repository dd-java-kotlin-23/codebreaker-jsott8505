plugins {
    alias(libs.plugins.android.application)
 //   alias(libs.plugins.hilt)
  //  alias(libs.plugins.navigation.safeargs)
}

android {

    namespace = projects.property("basePackageName") as String
    compileSdk = (project.property("targetSdk")as String).toInt()

}

dependencies{
    implementation(project(":client"))
    implementation(project(":services"))

    implementation(libs.app.compat)
    implementation(libs.activity)
    implementation(libs.fragment)
    implementation(libs.constraint.layout)
    implementation(libs.recycler.view)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)

}