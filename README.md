# barapp-android

These are some usefull apps for 0100 Campus internal usage.

## Credentials
Access credentials are not included to this repo.

We are using `mfuerstenau/gradle-buildconfig-plugin` and `etiennestuder/gradle-credentials-plugin`.

In order to add your credentials, run the following commands inside your source dir:
```
./gradlew addCredentials --key API_BASE_URL --value <host>
./gradlew addCredentials --key API_USERNAME --value <username>
./gradlew addCredentials --key API_PASS --value <pasword>
```

Credentials will be stored locally in your HOME gradle directory.

If running `./gradlew` does not work, run `chmod +x ./gradlew` before.
