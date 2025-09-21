<!-- HEADER -->
<p align="center">
  <img src="https://d1lmq142maiv1z.cloudfront.net/NIBM_City_University_Logo_2_213b4dd2f6.svg" alt="NIBM City University Logo" width="200"/>
</p>

<h1 align="center">✈️ Route Optimization and Passenger Boarding Tool 🛫</h1>
<h3 align="center">The project is submitted in partial fulfilment of the requirement of the Higher National Diploma in Software Engineering National Institute of Business Management</h3>
<p align="center">
  A tool that optimizes airline routes using Dijkstra’s Algorithm and streamlines passenger boarding with Max Heap + Queue for fair, efficient, and cost-effective operations.
</p>

<p align="center">
  <img src="https://media.giphy.com/media/3o7aCTfyhYawdOXcFW/giphy.gif" width="300"/>
</p>

<p align="center">
  <a href="#"><img alt="Platform" src="https://img.shields.io/badge/platform-Java%20%7C%20DataStructures-blue?style=for-the-badge"></a>
  <a href="#"><img alt="License" src="https://img.shields.io/github/license/itsvinz23/Route-Optimization-Boarding?style=for-the-badge"></a>
  <a href="#"><img alt="Contributions" src="https://img.shields.io/badge/contributions-welcome-brightgreen?style=for-the-badge"></a>
</p>

---

## 🚀 Overview

The **Route Optimization and Passenger Boarding Tool** enhances airline operations by:

- 🛣️ Finding shortest & cheapest routes using **Dijkstra’s Algorithm**  
- 👥 Managing passengers via **Max Heap + FIFO Queue**  
- 🎟️ Prioritizing **Business Class** while ensuring fairness for **Economy**  
- ⏳ Handling **special assistance** smoothly  
- 🔄 Dynamically updating boarding lists during operations  

---

## 🧩 Features

| 🛠 Feature                      | 💬 Description                                           |
|--------------------------------|---------------------------------------------------------|
| 🛣️ Route Finder                 | Dijkstra’s Algorithm on distance & cost separately      |
| 🎟 Boarding Priority            | Max Heap prioritization (Business > Economy)            |
| ⏳ FIFO Fairness                | Queue ensures fairness for same-priority passengers     |
| 🦽 Special Assistance Handling  | Smooth boarding for priority passengers with needs      |
| 📊 Comparison Display           | Routes with distance & cost shown in UI                 |
| 🔄 Dynamic Updates              | Boarding list updates in real-time                      |

---

## 📸 Demo Preview

<p align="center">
  <img src="https://media.giphy.com/media/26BRrSvJUa0crqw4E/giphy.gif" width="400"/>
</p>

*Replace with your tool’s demo screenshot or GIF.*

---

## ⚙️ Functional Overview

### 🔹 Input
- Airport details: names, codes, routes (distance, cost, duration, capacity)  
- Passenger details: name, ID/passport, class (Business/Economy)  
- Boarding info: check-in time, special assistance flag  

### 🔹 Process
- Build graph → airports as nodes, routes as weighted edges  
- Run Dijkstra’s Algorithm (distance & cost)  
- Insert passengers into Max Heap (priority = class + check-in order)  
- Apply FIFO Queue for tie-breaking  
- Dynamically update boarding  

### 🔹 Output
- 📍 Shortest path (total distance)  
- 💵 Cheapest path (total cost)  
- 📊 Route comparison (distance & cost)  
- 🎟 Passenger boarding order (priority + fairness)  
- 👥 Current passengers being served  

---

## 🛠️ Data Structures & Algorithms

- **Graph + Dijkstra’s Algorithm** → Route optimization  
- **Max Heap + Queue** → Boarding management  
- **Date & Time Comparison** → Check-in + boarding updates  

---

## 📁 Folder Structure

Route-Optimization-Boarding/
│
├── src/ # Java source files
├── assets/ # GIFs, screenshots, diagrams
└── README.md # Project documentation

---

## 🔮 Future Enhancements
- 🌍 Real-world flight API integration  
- 📲 Mobile app for staff/passenger use  
- 🤖 AI-based passenger flow prediction  
- 🧳 Baggage handling integration  

---

## 🙋‍♂️ Contributors

<table>
  <tr>
    <td align="center"><b>COHNDSE242F-049</b><br/>H A K R R LALANA</td>
    <td align="center"><b>COHNDSE242F-064</b><br/>O J VINSURA</td>
    <td align="center"><b>COHNDSE242F-065</b><br/>K P J RUKSHAN</td>
    <td align="center"><b>COHNDSE242F-066</b><br/>T M T N KUMARI</td>
  </tr>
</table>

---

## 🏫 Project by

<p align="center">
  <img src="https://d1lmq142maiv1z.cloudfront.net/NIBM_City_University_Logo_2_213b4dd2f6.svg" width="150" alt="NIBM Logo"/>
</p>

**National Institute of Business Management (NIBM)**  
School of Computing · Colombo 07 · 2025  

---
