# Flyweight Design Pattern Implementation
- This project shows the difference of memory consumed between normal object generation and using the flyweight design pattern.
- To see the comparison:
  - Open Task Manager (or similar applications but this tutorial is for Task Manager).
  - On the left tab, select "Details" or from any processes (some processes might not be applicable), right click and select "Go to Details".
  - Run the executable "Flyweight-Balls.jar" and press "Start".
  - On Task Manager find a process with the name "java.exe", "javax.exe", or anything similar, then observe the Memory tab when the number of balls generated reaches the 100 count.
    ![image](https://github.com/user-attachments/assets/3601d06a-b3eb-4036-b1ab-f0a7b44619c0)
  - Roughly remember the peak memory used on the first "normal" run, close the executable "Balls" window.
  - Open a new window using the executable "Flyweight-Balls.jar", tick the Flyweight checkbox, and press "Start".
  - Again, on Task Manager > Details, observe the lesser unit in the Memory tab when the 100th ball count is reached.
    ![image](https://github.com/user-attachments/assets/e0f86de1-1dec-45b8-98de-c1ce4ce29e65)

Notes:
  - I still did not know how to handle the memory leak when stopping the current run of ball generation. This means stopping the ball generation, ticking the "Flyweight" checkbox, the starting a new ball generating run does not display an accurate memory consumption in the Task Manager. So the current way to observe this is by closign the program after one run.
