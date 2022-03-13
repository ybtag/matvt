# MATVT: Mouse for Sonim XP3 Plus
This is an attempt to get [MATVT](https://github.com/virresh/matvt) to work on the Sonim XP3plus flip phone, despite Google's mistakenly breaking the API in Android 11.
See [here](https://github.com/virresh/matvt/issues/28#issuecomment-1020814825) for more details.

[@virresh](https://github.com/virresh) has now pushed his experimental adb branch, so this branch is trying to implement that approach for the XP3plus.

# Reporting Bugs
Please do not report bugs from this fork to @virresh. This is an experimental, hacky and independent fork.

# Contributing
All kinds of contributions are welcome. Two ways of contributing:  
- To contribute with your skills, please feel free to create a pull request in this repository. Bug reports can be done in the issues tab, and documentation happens similar to code contributions
- To sponsor the project, you can buy @virresh a coffee via https://ko-fi.com/Z8Z74EEHQ.

# Credits
A very big Thank you to [@virresh](https://github.com/virresh) for making the MATVT app and all his hard work maintaining it!

Thanks to EVA Facial Mouse for open sourcing their code. I've taken lots of ideas from their codebase. You can check them out at https://github.com/cmauri/eva_facial_mouse  
Thanks to [@sweenwolf](https://github.com/sweenwolf) for making this app work on remotes with less buttons, and for the app icons, fully transparent cursor images, boss key autodetect and autoscroll on borders!    
Thanks to TechDoctorUK for making a demo video  
Thanks to @hotcereal_twitter for providing more cursor images. [Link](https://gitter.im/virresh/community?at=6102e7b0d8381a2a839bbcfd).    

# Disclaimer
Please note that this project in no way, shape or form is sponsored by anybody. It's a fully independent project with generous folks contributing to it.
It does not generate any kind of revenue. There are no ads in the app and will never be. There's no tracking code stealing information or any kind of paywall for *extra* features. It is what it is, unless of course you download a MOD. Unless the MOD is open source, there's no guarantee it'll follow the same spirit as the above. The only way to contribute is either by (A) fixing known issues / helping out with code or (B) sponsoring the project

# APK Download 
The adb version can be downloaded here : [prerelease](https://github.com/ybtag/matvt/releases/download/sonim-xp3-adb/sonim-matvt-adb.apk)

# Installation

Use adb to install the app. Then run these commands:

 ```adb shell pm uninstall -k --user 0 com.lge.voicecommand
  adb shell appops set io.github.virresh.matvt SYSTEM_ALERT_WINDOW allow
  adb shell settings put secure accessibility_enabled 1
  adb shell settings put secure enabled_accessibility_services io.github.virresh.matvt/io.github.virresh.matvt.adb.services.MouseEventService
  adb tcpip 5555
  ```

Unfortunately in Android 11 the tcpip port of Wireless debugging is randomized, so it's necessary to input adb tcpip 5555 on the PC every time the phone is started. I would have to build a port scanner to avoid this.
