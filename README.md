# MyProjectManager is and android app for managing Projects(Trello alternative). 
It gives you an ability to:
* Create/Delete Boards (edit Board picture and name)
* Crete/Delete Lists inside Boards (edit List name)
* Create/Delete Cards inside Lists (edit Card name, assign user, add label colors, add due date)

MyProjectManager uses FireBase Authentication SDK, Firebase Cloud Firestore, Firebase Storage

## Tests
* Espresso framework
* Robot pattern architecture
* Gradle/Fork runner
* Implementation of Counting Idling Resource

## Run Espresso Instrumentation tests with the Fork
From the command line execute the following Gradle commands:
```
./gradlew --stop
```
```
./gradlew clean forkDebugAndroidTest
```

### Code Style
To verify the code format, you can run:
```
./gradlew clean ktlint
```
