✈️ Route Optimization and Passenger Boarding Tool

The Route Optimization and Passenger Boarding Tool helps airline staff efficiently plan routes and manage passenger boarding.
It uses Dijkstra’s Algorithm to find the shortest or cheapest flight paths and applies a Max Heap + Queue mechanism to prioritize passenger boarding based on class and check-in time.

This tool improves airline operations by saving time, reducing costs, and ensuring fair yet efficient boarding.

📌 Features

✅ Find shortest and cheapest routes between airports using Dijkstra’s Algorithm
✅ Manage passenger boarding with Max Heap + FIFO Queue
✅ Prioritize Business Class passengers while keeping fairness for Economy
✅ Display routes with distance and cost comparisons
✅ Handle special assistance passengers smoothly
✅ Update boarding list dynamically during operations

⚙️ Functional Overview

🔹 Input

Airport details: names, codes, routes with distance, cost, duration, capacity

Passenger details: name, ID/passport number, class (Business/Economy)

Boarding info: check-in time, special assistance flag

🔹 Process

Build graph with airports as nodes and routes as weighted edges

Run Dijkstra’s Algorithm on distance and cost separately

Insert passengers into Max Heap (priority = class + check-in order)

Use FIFO Queue for same-priority passengers

Update boarding list dynamically during operation

🔹 Output

Shortest path (with total distance)

Cheapest path (with total cost)

Route results shown in UI for passenger comparison

Passenger boarding order (priority + arrival order)

Current passengers being served

🛠️ Data Structures & Algorithms

Graph + Dijkstra’s Algorithm → Fast route optimization by distance & cost

Max Heap + Queue → Efficient boarding order (priority + FIFO fairness)

Date & Time Comparison Algorithm → Manages check-in and boarding updates



🎓 Project Info

Higher National Diploma in Software Engineering (HNDE)
Course Module: PDSA Project Proposal – 24.2F/CO

👨‍💻 Team Members:

COHNDSE242F-049 - H A K R R LALANA

COHNDSE242F-064 - O J VINSURA

COHNDSE242F-065 - K P J RUKSHAN

COHNDSE242F-066 - T M T N KUMARI

Submitted to:
National Institute of Business Management (NIBM) – School of Computing, Colombo 07
