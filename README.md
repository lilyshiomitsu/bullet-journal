[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/x6ckGcN8)
# 3500 PA05 Project Repo

[PA Write Up](https://markefontenot.notion.site/PA-05-8263d28a81a7473d8372c6579abd6481)
![Screenshot 2023-06-18 at 10.12.56 PM.png](Screenshot%202023-06-18%20at%2010.12.56%20PM.png)![Screenshot 2023-06-22 at 1.12.03 PM.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fsl%2F8jj8vhw51799n82rqqqy0yw00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_VxgfTu%2FScreenshot%202023-06-22%20at%201.12.03%20PM.png)![Screenshot 2023-06-22 at 1.12.45 PM.png](Screenshot%202023-06-22%20at%201.12.45%20PM.png)![Screenshot 2023-06-22 at 1.12.45 PM.png](Screenshot%202023-06-22%20at%201.12.45%20PM.png)

Introducing the Ultimate Bullet Journal App - Your Personal Organizer and Productivity Companion!

Are you tired of juggling multiple calendars, to-do lists, and notes to keep your life on track? Look no further! Our revolutionary Bullet Journal App is here to transform your planning experience and empower you to achieve your goals with ease. Seamlessly blending creativity and functionality, our app is designed to be your digital ally, assisting you every step of the way.

Section 1: Requirements

Week View: Get a comprehensive overview of your entire week at a glance. Easily navigate through days, ensuring you never miss an important event or task.

Event and Task Creation: Effortlessly create events and tasks, set due dates, assign priorities, and even add helpful reminders. Stay organized and in control of your commitments.

Commitment Warnings: Our app understands the value of your time and the importance of sticking to your plans. Receive timely notifications and reminders to keep you on track and ensure you never miss a deadline or appointment.

Persistence: Your data is securely stored and readily accessible across devices, so you can seamlessly transition from your smartphone to tablet or computer without missing a beat.

Section 2: Headlining Features

Task Queue: Take charge of your tasks with our intuitive Task Queue. Prioritize, rearrange, and check off completed tasks effortlessly, ensuring you never lose sight of what needs to be done.

Categories: Customize your organization system with personalized categories. Categorize events, tasks, and notes for easy filtering and quick reference, allowing you to focus on what matters most.

Section 3: Power Ups

Quotes and Notes: Stay inspired and motivated with our built-in Quotes and Notes feature. Capture your thoughts, jot down ideas, and store meaningful quotes to keep your creativity flowing.

Progress Bar: Track your progress and see how far you've come. With our Progress Bar, you can visually monitor your achievements, providing the satisfaction of seeing your goals materialize.

Weekly Overview: Gain insightful perspectives on your productivity and accomplishments with our Weekly Overview. Reflect on your past week's activities and set intentions for the week ahead.

Section 4: Quality of Life

Links: Seamlessly integrate your favorite apps and websites into your bullet journal. Save links related to your tasks and events, allowing you to access relevant resources with a single tap.

Mind Changes: We understand that life is dynamic, and plans change. Easily modify and reschedule events and tasks, ensuring your bullet journal remains flexible and adaptable to your evolving needs.

Section 5: Extra Credit

Visual Flourish: Immerse yourself in a visually stunning and engaging interface. Enjoy beautiful themes, captivating animations, and elegant designs that make planning a delightful experience.

Splash Screen: Start your planning journey with a personalized splash screen. Choose from a selection of inspiring images or upload your own, setting the tone for your productive day ahead.

Privacy Lock: Your privacy is our utmost priority. Safeguard your bullet journal with a privacy lock feature, providing an extra layer of security for your sensitive information.

Weekly Starters: Embrace each week with purpose and intention. Explore our collection of curated weekly starters, including goal-setting exercises, inspirational prompts, and productivity tips to help you make the most of your week.

Upgrade your planning experience with the Ultimate Bullet Journal App. Seamlessly manage your events, tasks, and notes while fostering creativity and boosting productivity. Download now and embark on a journey towards organization, motivation, and success!

How we used the SOLID principles: 

S - single responsibility principle 
We implemented this by making sure that each of our methods specializes in handling
a specific part of our code, and that each of our classes handled only the amount of 
information that they needed to have.

O - we made sure that our program is open for extension but closed for modification
by making sure that all parts of our code are modular and are able to accept new arguments
without breaking previous implementations.

L - We abstracted parts of our code, and used Union data types which are able to work
if the superclass is replaced by an instance of its subclasses.


I - We implemented this part by making sure that any subclasses did not implement
any methods that they did not need. 

D - We made sure to give each class two types of constructors, one of which allowed
us to inject information into classes, making the dependencies from big models not rely
on models smaller than themselves.

How we could implement Auto #Tags: 
We could extend our program to implement Auto #tags by using the JSON deserializer
and checking for any # and adding them as categories automatically. We would probably 
use a similar implementation as with PA01 and PA02 to detect when the user used a #
to create a new category. 