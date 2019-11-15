# Git Manual <!-- omit in toc -->

## Table of contents <!-- omit in toc -->

- [1 Introduction](#1-introduction)
- [2 Project Setup](#2-project-setup)
  - [2.1 Software Requirements](#21-software-requirements)
  - [2.2 Development Environment Setup](#22-development-environment-setup)
  - [2.3 GitKraken](#23-gitkraken)
    - [2.3.1 Set up](#231-set-up)
    - [2.3.2 Basics](#232-basics)
    - [2.3.3 Branching](#233-branching)
- [3 Coding standards](#3-coding-standards)
  - [3.1 IntelliJ setup](#31-intellij-setup)
- [4 Git Branching Strategy](#4-git-branching-strategy)
- [5 Versioning](#5-versioning)
- [6 Instructions for PR reviewers](#6-instructions-for-pr-reviewers)

## 1 Introduction

Written by [David Mares](https://github.com/ka5p3rr) the SQA Manager in collaboration with the team leader [Andrea Cerasoni](https://github.com/acerasoni).

First chapter of this document explains how to setup your development environment. Second part sets the standard and conventions to be followed by the project contributors. It specifically focuses on coding, VCS practices and our custom workflow.

Please follow the instructions carefully.

## 2 Project Setup

### 2.1 Software Requirements

To ensure compatibility and easy start we recommend using IntelliJ IDEA as your IDE and Java 8. Download the current versions of the following tools:

- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
  - if you are unfamiliar with this tool refer to [help guide](https://www.jetbrains.com/help/idea/installation-guide.html)
- [Java 8 JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [git](https://git-scm.com/)

### 2.2 Development Environment Setup

1. `git clone` the repository
2. Open IntelliJ -> "Import Project" -> /src/build.gradle

### 2.3 GitKraken

We get a free GitKraken PRO license from GitHub Student Developer Pack. If you haven't signed up already go to [Student Developer Pack - GitHub Education](https://education.github.com/pack). Click Join GitHub Education in the top right corner and follow the instructions. GitKraken is a great git tool as it provides user-friendly GUI and functionality for local and remote branching, commit messages, merging, pull requests and much more.

#### 2.3.1 Set up

Download [GitKraken](https://www.gitkraken.com/download) and install it. Once the installation is done you are asked to sign in. Just click the Sign in with GitHub account button and connect your GitHub account with GitKraken. Next step is to set up your local git profile. In the Profile set up dialog leave your Profile Name as default and set your Name (should be loaded automatically). Email setup is a little bit more complicated. It is important to check what your GitHub email is, because you might have set your email as private and GitHub generated a special email address for you to use. Please log in to GitHub and go to settings, under Emails look for Keep my email address private. If the box is checked then you have set your email to be private and you need to use the generated one, which will be specified bellow (`xxxxxx@users.noreply.github.com`). Copy the email address and paste it to the GitKraken email field. Otherwise, if you have not set your email as private just use your Primary profile email address. Save the changes.

All the values you have just set in GitKraken are saved in your .gitconfig file, which has all the git configuration information used for your authentication, settings etc. To view the gitconfig file type `git config --list` in the Git Bash (which is part of the git installation on Windows, just search for it in your apps).

In GitKraken click the Open a Project button, go to the Clone section. Click GitHub.com in the list and scroll all the way down, where you should see the shared project. Choose where you want to clone the remote repository and click Clone the repo! button. Once the cloning is done, click Open Now and when the project loads click the down arrow next to the Pull button in the top bar and choose Pull (rebase) as your default by clicking the empty circle next to it. Now close GitKraken.

Open Git Bash and using cd commands navigate to the directory of the cloned project. When you are in the directory run: `git config --local commit.template "./instructions/.gitmessage"` in your Git Bash. This command will set the .gitmessage file as our default commit message template.

Now you can close the Git Bash and open up GitKraken again. In the top left corner click on file and go to preferences. Under Repo-Specific Preferences go to Commit Template and make sure that Apply this template to commit messages and Remove comments from commit messages are checked. Also there should be some text in the Description section (please don't change that). Now you can Exit Preferences.

> Remember: You have to run the Git Bash template command and change the Repo-Specific Preferences every time you clone the repository, because the settings are individual per project.

That is basically it as far as the initial setup goes.

#### 2.3.2 Basics

GitKraken is really easy to use, in the middle you see a visual representation of the current remote and local branches and also individual commits with their authors. You can click on each commit and see what files have been changed, who made the changes etc. When you make a change locally on your machine, you can view it by clicking the very first line in the middle section (the one with the empty circle). Or there will be a little bar in the top right corner saying View changes. On the right you should now see a new section. The top part shows you all the changes (file changes, renaming, deletions etc.) that you have made. To upload your changes to our GitHub repository you need to follow these steps:

1. Stage all changes
    - button in the right section
        - you can either stage all files
        - or just some of them
    - staging is basically making your files ready for commit
        - it moves the files from unstaged to staged state
2. Writing a commit message
    - there are two parts to every commit message
        - the summary
        - the description/ additional details

> Important: there is a commit template you should see in the commit message box (please follow the instructions specified). If you decide to use command line or your IDE git features, please make sure you always follow the commit template .gitmessage located in the instructions folder. When using Git Bash after adding tracked files run the commit using `git commit`!

1. Commit your changes
    - this command logs all your changes locally
2. Push
    - push will upload all your commits to our remote repository

#### 2.3.3 Branching

Another very useful feature of GitKraken is in the left panel, where you can see all the remote branches of the GitHub repository and also your local personal branches. There is also a functionality that allows us to create Pull Requests directly from GitKraken.

## 3 Coding standards

We will be following the Google Style Guides as our coding standards. Specifically we will be coding in Java and use the standards described [here](https://google.github.io/styleguide/javaguide.html).

### 3.1 IntelliJ setup

Download the [google-java-format](https://plugins.jetbrains.com/plugin/8527-google-java-format) plugin. To trigger the plugin in IntelliJ go to *Code -> Reformat Code*. I also recommended to set the code style settings which help to create properly formatted code as-you-go.

1. Download [intellij-java-google-style.xml](https://raw.githubusercontent.com/google/styleguide/gh-pages/intellij-java-google-style.xml).

2. Go to *File -> Settings -> Editor -> Code Style*.

3. Click on the wrench icon and import the downloaded file.

4. Make sure that GoogleStyle is chosen as the current Scheme.

Those settings can’t completely mimic the format enforced by the Google Style Guides. So before submitting code, please make sure to run *Reformat Code* and follow the standards as close as possible.

## 4 Git Branching Strategy

We have 4 branch tiers called: Production, Development, Epics and Local designated branches (can be either Feature or Bugfix).

1. Production (called `prod`)
   - Fully reviewed code, released current version of the product
   - Updated via Pull Request from Development requiring 5 reviewers
     - Additionally this will include an FTR

2. Development (called `dev`)
   - Current stable version
   - Updated via Pull Request from Epic requiring FTR meeting

3. Epics (called `epic/xxxx`)
   - Feature based, composed of multiple Local designated branches
   - Updated via Pull Request from Local designated branches requiring 2 reviewers

4. Local designated branches (called `component/xxxx` or `bugfix/xxxx`)
   - Variable in purpose
   - Updated via commits
   - Comprise of the smallest possible component implementation related to the feature

## 5 Versioning

In this chapter we define our custom versioning convention.

Our product version comprises of three numbers separated by dots - `x.y.z`, where `x` is major, `y` is minor, and `z` is patch. These have no numerical limit. The version is specified in the `build.gradle` file and has to updated manually as follows:

- When merging into `prod` major should be increased by 1 and minor and patch should be reset back to 0
- When merging into `dev` major stays the same, minor increases by 1 relative to the current minor version, and patch is reset to 0
- When merging into `epics` major and minor stay the same, and patch increase by 1 relative to the current patch version

## 6 Instructions for PR reviewers

If you are added as a reviewer ensure the following:

1. Code has no merge conflicts
   - if yes resolve them

2. Follow versioning convention specified [above](#5-versioning)

3. Review the code to the best of your ability -> check for bugs, sanity code check, etc.

4. Ensure all tests and checks passed on CI
