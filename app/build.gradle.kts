plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.sqltestpassword"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sqltestpassword"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

   // implementation("com.hanks:passcodeview:1.0.6")
  //  implementation("com.github.hanks-zyh:PasscodeView:1.0.6")
   // compile("com.hanks:passcodeview:0.1.2")
 //   implementation("androidx.legacy:legacy-support-v4:1.0.0")
}