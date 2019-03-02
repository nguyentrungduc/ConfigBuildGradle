# ConfigBuildGradle
## Gralde 
- Gralde là bộ công cụ build ứng dụng hỗ trợ nhiều ngôn ngữ. Nếu bạn đang muốn build, testing hay publish ứng dung trên bất kì nền tảng nà, Gradle cung cấp một mô hình linh hoạt có thể hỗ trợ toàn bộ vòng đời từ complie, đóng gói code cho đến pulic ứng dụng. Gradle hỗ trợ tự động hóa xây dựng nhiều ngôn ngữ và nền tảng bao gồm Java, Scala, Android, C/C++ và Groovy, nó được tích hợp sẵn trên các ide phổ biến như Eclipse, InteliJ, AndroidStudio, ...
## Gralde in Android Stuido 
- Android Studio sử dụng Gralde làm công cụ build ứng dụng nhờ nó mà ta có thể quản lí các version built một cách dễ dàng. Với mỗi cấu hình cài đặt nó định nghĩa source code và resource, trong khi sử dụng lại các phần chung cho tất cả các phiên bản ứng dụng.
- Gradle và Android plugin chạy độc lập trong Android Studio. Điều này có nghĩa là có thể built Android app trong Android Studio bằng command line bằng Gralde Wrapper.
- Gradle sử dụng Groovy làm DSL nó miêu tả và điều khiển quá trình built ứng dụng.
- Groovy được xuất hiện từ năm 2013 khi Google IO công bố Android Studio sử dụng Gradle build với groovy script. Groovy là một ngôn ngữ lập trình hướng đối tượng trên nền Java. Nó là một ngôn ngữ lập trình động với các tính năng tương tự như Python, Ruby, Perl, và Smalltalk. Hơn nữa, nó cũng có thể được sử dụng như là một ngôn ngữ kịch bản chạy trên nền máy ảo Java.
## Task
- Nói không sai Task chính là trái tim của Gradle. Một Task trong Gradle đơn giản thì là một đơn vị công việc mà Gradle có thể hiểu và chạy được nó và phần core của Task chính là Action.

- Giả sử chúng ta có thể mô tả một task compile một vài Java sources hoặc copy một số file từ thư mục này sang thư mục khác, hay đơn giản chỉ là in ra dòng chữ "Hello Gradle". Một task có thể làm những việc độc lập như in ra dòng chữ "XXX" hoặc có thể chạy tạo các dependencies với những Task khác. Gradle sẽ đảm bảo tất cả dependencies sẽ được chạy.

- Một task còn có thể định nghĩa inputs và outputs, như một java method, ngoài ra nó còn có thể config để đọc và ghi file nữa.
- Chúng ta có thể tự tạo ra một task sử dụng Grovvy hay Kotlin để chạy chúng. 
## Gradle Wrapper
- Tìm hiểu về Gradle Wrapper là cách nhanh nhất tiếp cận với Gradle build. Nó là một batch script trên Windows, và shell scprit trên các thiết bị khác. Ta có thể chạy các Task build gradle với terminal của android bằng ./gradlew task-name gradlew(gradlew(rapper).
- Ta có thể xem list  built task có sẵn trong project bằng:  gradlew tasks
- Gradle Wrapper gồm số file trong project của bạn 
+ gradlew.bat : Bat script in window mà chúng ta có thể chạy các task
+ gradle/wrapper/gradle-wrapper.jar: đây là nơi chứa code của gradle wrapper
+ gradle/wrapper/gradle-wrapper.properties : đây là nơi chứa thuộc tính cấu hình gradle wrapper
## Config Build Gradle
### Config Build Type
- Khi build app của bạn ta có thể tùy chọn kiểu build mà ta đã định nghĩa trong build type. Build type xác định những thuộc tính mà Gradle sử dụng khi build app của bạn, tùy theo quá trình phát triển mà ta build app khác nhau. Ví dụ khi build app debug sẽ bật trình debug lên, còn khí build app bản release ta cần bảo vệ source code và không cần bật chế độ debug, ta có thể tạo thêm một số thuộc tính cho những kiểu build này

        buildTypes {
              release {
                  minifyEnabled true
                  proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
              }

              debug {
                  applicationIdSuffix ".debug"
                  debuggable true
              }
              
              staging {
                  initWith debug
                  manifestPlaceholders = [hostName:"internal.example.com"]
                  applicationIdSuffix ".debugStaging"
              }
           }
       }
       
### Config Product flavor
Product flavor đại diện cho các phiên bản khác nhau mà chúng ta release cho user như phiên bản free hay phiên bản trả phí hay thông thường khi ta phát triển ứng dụng trong dự án có vài môi trường như DEV, Staging, Production. Ta có thể config chúng với Product flavor.
Lưu ý rằng tất cả các flavor phải có dimension cụ thể thuộc một nhóm flavors nào đó. Ta luôn phải gán flavors với một flavor dimension nào đó cho dù chỉ có một flavorDimensions nếu không sẽ build lỗi 

                Error:All flavors must now belong to a named flavor dimension.
                The flavor 'flavor_name' is not assigned to a flavor dimension.
 
 - Ví dụ 
 
                productFlavors {
                        demo {
                            // Assigns this product flavor to the "version" flavor dimension.
                            // This property is optional if you are using only one dimension.
                            dimension "version"
                            applicationIdSuffix ".demo"
                            versionNameSuffix "-demo"
                        }
                        full {
                            dimension "version"
                            applicationIdSuffix ".full"
                            versionNameSuffix "-full"
                        }
                    }
                    
- Sau khi config xong product flavors, click "Sync Now" . Gradle sẽ tự động tạo ra build variant nó thực chất là tổ hợp của buildType và product flavor với format <product-flavor><Build-Type>
        
#### Flavor Dimensions
- Trong một số trường hợp, ta cần kết hợp nhiều cấu hình từ nhiều product flavors. Ví dụ, ta muốn tạo nhiều cấu hình cho product flavor cho "full" với "demo" trên cùng 1 API. Để làm được việc này, Gradle cho phép chúng ta tạo ra các group của product flavor bằng flavor dimensions. Khi bạn build app, Gradle sẽ kết hợp nhiều cấu hình product flavor từ mỗi flavor dimensions mà ta địng nghĩa, kết hợp cũng với config build type để ra được build variant. Gradle sẽ không kết hợp product flavors từ cùng một flavor dimensions 

          flavorDimensions "api", "mode"

          productFlavors {
            demo {
              dimension "mode"
              ...
            }

            full {
              dimension "mode"
              ...
            }
            
            minApi24 {
              dimension "api"
              minSdkVersion 24
              versionCode 30000 + android.defaultConfig.versionCode
              versionNameSuffix "-minApi24"
              ...
            }

            minApi23 {
              dimension "api"
              minSdkVersion 23
              versionCode 20000  + android.defaultConfig.versionCode
              versionNameSuffix "-minApi23"
              ...
            }

            minApi21 {
              dimension "api"
              minSdkVersion 21
              versionCode 10000  + android.defaultConfig.versionCode
              versionNameSuffix "-minApi21"
              ...
            }
          }
          
Số build variant được tạo ra bằng số product (số flavor của mỗi flavor dimension) * số build type. 
- > Build variant: [minApi24, minApi23, minApi21][Demo, Full][Debug, Release]
### Filter Variants
- Gradle tạo ra build variant là tổ hợp của build type và product flavor mà mình cấu hình. Tuy nhiên, có một số variant mà ta không cần dùng đến. Ta có thể xóa nó đi bằng cách tạo ra Variant filter trong build.gradle file 

        variantFilter { variant ->
              def names = variant.flavors*.name
              // To check for a certain build type, use variant.buildType.name == "<buildType>"
              if (names.contains("minApi21") && names.contains("demo")) {
                  // Gradle ignores any variants that satisfy the conditions above.
                  setIgnore(true)
              }
          }
          
### Source Sets
- Theo mặc định khi tạo 1 project tất cả source code và resource đều nằm trong main/ với tất cả các phiên bản build varaints. Tuy nhiên ta có thể tạo ra một source set khác để phân bổ một cách hợp lí source code và resource của các phiên bản varaints. Ví dụ bạn có thể định nghĩa. 
- Để xem tổ chức của source set ta chỉ cần Gradle -> MyApplication > Tasks > android -> Run Task
### Configure signing settings 
- Gradle không kí bản APK release của mình trừ khi bạn config chúng ở gradle.
#### Step
1. Create keystore. Keystore là một file nhị phân bao gồm private key. Bạn cần giữ key store ở nơi an toàn và bảo mật
2. Create private key. Private key cho entity được định nghĩa trong app.
3. Thêm cấu hình singing config vào build.gradle

                android {
                    ...
                    defaultConfig {...}
                    signingConfigs {
                        release {
                            storeFile file("myreleasekey.keystore")
                            storePassword "password"
                            keyAlias "MyReleaseKey"
                            keyPassword "password"
                        }
                    }
                    buildTypes {
                        release {
                            ...
                            signingConfig signingConfigs.release
                        }
                    }
                }
                
 - Để gen ra signed APK, Build -> Generate Signed APK  
 ## Code coverage with Jacoco
 Code coverage là một số liêu phần mềm được sử dụng để đo lường số dòng code của chúng ta được test trong automateds test
 ### Set up
 
                 buildscript {
                  repositories {
                    google()
                    jcenter()
                  }
                  dependencies {
                    classpath 'com.android.tools.build:gradle:3.0.1'
                    classpath 'org.jacoco:org.jacoco.core:0.8.1' //Use latest version
                  }
                }
- Với các module chứa code test, cập nhật respective gradle và toolVersion như gradle của project
- Ta có thể gặp một số issues khi lấy coverage từ Robolectric test. Để có Robolectric test trong report ta cần đặt includeNoLocationClasses = true, trong tất cả các task test 
- Để nhận report trong các thiết bị debug -> testCoverageEnabled = true
- AndroidJUnitRunner chạy trên cùng các thiết bị, vì vậy về cơ bản các bộ test đang chạy, nó ảnh hưởng đến các test phụ thuộc vào trạ trạng thái. Để tránh việc này -> sử dụng ORCHESTRATOR trong testOptions

        apply plugin: 'jacoco'
        jacoco {
            toolVersion = '0.8.1' //Use latest version
        }

        tasks.withType(Test) {
            jacoco.includeNoLocationClasses = true
        }

        android {
            buildTypes {
                debug {
                    testCoverageEnabled true
                }
            }
            testOptions {
                execution 'ANDROID_TEST_ORCHESTRATOR'
                animationsDisabled true

                unitTests {
                    includeAndroidResources = true
                }
            }
        }

 







        









 
