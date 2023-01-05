
# HashKraken
## Created By: David Corvaglia 2022
### A multithreaded password cracker written in Java (JDK 17 LTS).
## Supported Algorithms:
- MD5
- SHA256
- Bcrypt
## Features:
- Multithreading (Built-In or Dictionary/Wordlist)
- CLI
- Written in Java
- Top 10 Million Passwords Built-In
- Ability to Add a Custom Wordlist or Dictionary
- Brute Force Password Cracking (Single Thread)
- Cross Platform
## Dependencies
- Java JDK 17 LTS  (Tested with [Amazon Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html))
- Maven: [Spring Framework Security Crypto](https://mvnrepository.com/artifact/org.springframework.security/spring-security-crypto)
## Setup:
1. Download JDK 17 (Recommend JDK is [Amazon Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html))
2. Download HashKraken.jar from the [Releases](https://github.com/corvad/HashKraken/releases) section.
## Usage:
`java.exe -jar HashKraken.jar [Hashing Algorithm] [Hash] [Threads]`
- Required Arguments: [Hashing Algorithm] [Hash] [Threads]
    - Hashing Algorithm: Algorithm to use for hashing. Can be "md5", "sha256", or "bcrypt."
    - Hash: Hash to match to plaintext password (must be of the same algorithm provided in first argument).
    - Threads: The number of threads to use when running the program (recommended not to go over the number of threads on your CPU).
- Default Mode Uses Top 10 Million Wordlist Bundled
- Additional Optional Arguments:
    - -p/--path [Wordlist Path]
        - If provided it will run in wordlist mode with the path provided.
    - **OR**
    -  -b/--bruteforce [Max Character Length of Combinations To Try (Will Only Use One Thread)]
        - If provided it will run in bruteforce mode until the max length of characters provided.
        - Bruteforce mode will always run single threaded.
- Note: Java.exe may be the wrong version depending on your computer, in this case make sure to navigate to the right java.exe of version 17.
## Example Usage:
1. `java.exe -jar HashKraken.jar md5 482c811da5d5b4bc6d497ffa98491e38 8`
    - Runs HashKraken using the built-in wordlist with MD5, a hash of 482c811da5d5b4bc6d497ffa98491e38, and running with 8 threads.
2.  `java.exe -jar HashKraken.jar md5 482c811da5d5b4bc6d497ffa98491e38 8 -p wordlist.txt`
    - Runs HashKraken using wordlist.txt as a dictionary with MD5, a hash of 482c811da5d5b4bc6d497ffa98491e38, and running with 8 threads.
3. `java.exe -jar HashKraken.jar md5 482c811da5d5b4bc6d497ffa98491e38 1 -b 4`
    - Runs HashKraken using bruteforce mode with bruteforce character length of 4 with MD5, a hash of 482c811da5d5b4bc6d497ffa98491e38, and running with 1 thread.
# Building the Jar in Intellij Idea
1. Open the project in Intellij Idea with [Amazon Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html) installed and configured as the JDK for the project.
2. Select the build menu and select build artifacts.
3. Run the Build task.
4. Jar will be output to /out/artifacts/HashKraken_jar/HashKraken.jar

# License:
### MIT License [Click here to see the license](https://github.com/corvad/HashKraken/blob/main/license.md)
