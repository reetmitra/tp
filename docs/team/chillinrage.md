---
layout: page
title: Chong Chan How's Project Portfolio Page
---

## Project: NUSContacts

NUSContacts is a desktop app designed for NUS Students, offering a streamlined way to manage their academic contacts with ease.

Given below are my contributions to the project.

### Code contributed
You can find the overall code using this [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=chillinrage&breakdown=true).


### New features
* Added new **course** field to contacts. 
([#55](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/55){:target="_blank"})

  * **What it does**: A new field for users to store the course code of a contact.
  * **Highlights**: This feature affects existing commands (`add`, `edit` etc.) and allows for another enhancement ("Find contacts by course code" implemented by **Hibeom0929** [#95](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/95){:target="_blank"}).
  * **Challenge**: As this was my first contribution to the project, this required me to conduct an in-depth analysis of the existing code, from the parsing and checking of prefixes to setting up of unit tests.

* Added a history command that allows the user to navigate to previous successful commands using up/down keys. ([#102](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/102){:target="_blank"})

  * **Impact**: Allows ease of toggling between past commands, which also allows users to keep track of the commands they have executed.

* Added new `undo` command.
([#131](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/131){:target="_blank"})
  * **What it does**: A new command for users to undo the last change made to the contact list. This only undo commands such as `add`, `delete`, `edit`, etc. but cannot undo commands such as `list`, `help`, etc. since they do not modify the contact list.
  * **Impact**: Allows users to recover from errors if they had entered the wrong command. 
  * **Possible improvements**: Complementary `redo` feature in case the user wants to revert the changes back, as well as improving `undo` to also work when used consecutively (to undo last few changes).


### Enhancements to existing features
* `delete` command now supports multiple indices as parameter.
([#74](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/74){:target="_blank"})
  * **What it does**: Allows users to delete multiple contacts with just one command.
  * **Impact**: Streamline multiple deletions, which is applicable in the context of NUS students being the main users. Reason is that students will change courses every semester, which means that users might want to remove most of the existing contacts (except for a few such as close peers). Trying to delete them 1 by 1 would be slow and frustrating to users. This enhancement would mitigate that frustration by compressing all those lines into just one command.
  * **Possible improvements**: Improve parsing to allow range deletions (i.e. `1 - 10`) rather than individual indices.

* `esc` key now closes help window.
([#90](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/90){:target="_blank"})
  * **What it does**: Users can press `F1` to open up a small help window. Then pressing `esc` key allows users to close that window and return to the main application.
  * **Impact**: Quality of life improvement as it allows users to quickly close the window without having to press `alt` + `f4` or navigating the cursor to the `x` button.

* Improve parser to understand and execute unambiguous command prefix
([#70](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/70){:target="_blank"})
  * **What it does**: Users can type the prefix of a command and the application will still execute the intended full command if the entered prefix clearly matches the full command. (i.e. `a` will be interpeted as `add` and `del` will be interpreted as `delete`). However, prefixes that are ambiguous will still result in an error (i.e. `c` can mean `copy` or `clear`).
  * **Impact**: Quality of life improvement as it allows users to type the shortened form of the command instead of the full word.

* Wrote additional tests for the new features as well as update existing tests to accommodate new features. These tests are updated concurrently with the new feature implementations so the changes are reflected in the same pull requests. (
    [#55](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/55){:target="_blank"},
    [#70](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/70){:target="_blank"},
    [#74](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/74){:target="_blank"},
    [#90](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/90){:target="_blank"},
    [#102](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/102){:target="_blank"},
    [#131](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/131){:target="_blank"})

### Team-based tasks
* Update application title to "NUSContacts". 
[#27](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/27){:target="_blank"}
* Update jar and log file names to "NUSContacts".
[#29](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/29){:target="_blank"}

### Documentation
* User Guide
  * Added documentation for multiple indices deletion and undo command.
  * Added new FAQ for checking and installation of Java 11.
  ([#211](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/211){:target="_blank"})
  * Update existing description about extraneous parameters.

* Developer Guide
  * Added Non-functional Requirements (NFR).
  ([#14](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/14){:target="_blank"})
  * Added manual testing instructions for multiple indices deletions.
  ([#213](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/213){:target="_blank"})
  * Added manual testing instructions for adding contacts.
  ([#216](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/216){:target="_blank"})


### Community
* PRs reviewed (with non-trivial comments): (
    [#119](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/119){:target="_blank"},
    [#136](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/136){:target="_blank"},
    [#138](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/138){:target="_blank"})
* Reported bugs and suggestions to other team in PE dry run. (full list 
[here](https://github.com/ChillinRage/ped/issues){:target="_blank"})

### Tools
* Integrate TestFX to the project for JavaFX testing.
([#90](https://github.com/AY2324S2-CS2103T-T11-2/tp/pull/90){:target="_blank"})