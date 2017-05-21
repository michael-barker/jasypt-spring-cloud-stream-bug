# Jasypt Spring Cloud Stream Bug

When combining Spring Cloud Stream with Jasypt default properties that are overridden in the active profile are still being decrypted.  If the encryptor keys are different for no profile and an active profile then the decryption of the overridden value fails causing the app to not start.

When not specifying a profile the app will fail to start if there Zookeeper and Kafka are not running on localhost with default ports.  Running the app with `other` profile will cause it to fail before connecting to Zookeeper/Kafka.

##Steps to reproduce

Run app with `other` profile
```bash
java -jar build/libs/stream-jasypt-bug-0.0.1-SNAPSHOT.jar --spring.profiles.active=other
```
