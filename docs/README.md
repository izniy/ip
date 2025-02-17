# 🐻 BearBot - Your Personal Task Assistant

![Ui picture](./docs/Ui.png)

Tired of forgetting deadlines? 
Overwhelmed by your to-do list? 
Meet **BearBot**, your adorable and reliable task management assistant! 🐻✨

BearBot is more than just a chatbot— it's your personal productivity buddy, 
always ready to help you stay on top of your tasks with a simple and intuitive interface. 
Whether you're tracking deadlines, planning events, or just organizing your daily to-dos, 
BearBot keeps things fun, friendly, and efficient!

With BearBot, managing your tasks is as easy as having a chat. 
Plus, with its **cute bear personality**, you'll never feel alone while tackling your to-do list! 🐾💙


## 🚀 Features
✅ **Task Management:** Add, delete, mark, unmark and list tasks  
✅ **Deadlines & Events:** Schedule tasks with specific due dates  
✅ **Data Persistence:** Saves tasks automatically between sessions
✅ **Archiving:** Remove all completed tasks with a single command


## 📖 How to Use BearBot
### 1️⃣ Adding a Todo Task
```
todo Read a book
```

Expected output: 
```
Got it! I've added this task:
[T][ ] Read a book
```

### 2️⃣ Adding a Deadline Task
```
deadline Submit report /by 2025-02-20
```

Expected output:
```
Got it! I've added this task:
[D][ ] Submit report (by: Feb 20 2025)
```

### 3️⃣ Adding an Event
```
event Project meeting /from 2025-02-20 /to 2025-02-21
```

Expected output:
```
Got it! I've added this task:
[E][ ] Project meeting (from: Feb 20 2025 to: Feb 21 2025)
```

### 4️⃣ Listing All Tasks
```
list
```

Expected output:
```
Here are your tasks:
1. [T][ ] Read a book
2. [D][ ] Submit report (by: Feb 20 2025)
3. [E][ ] Project meeting (from: Feb 20 2025 to: Feb 21 2025)
```

### 5️⃣ Marking a Task as Done
```
mark 2
```

Expected output:
```
Nice! I've marked this task as done:
[D][X] Submit report (by: Feb 20 2025)
```

### 6️⃣ Deleting a Task
```
delete 1
```

Expected output:
```
Noted. I've removed this task:
[T][ ] Read a book
```

### 7️⃣ Archiving All Completed Tasks
```
archive
```

### 8️⃣ Exiting BearBot
```
bye
```


## 📜 Credits & Attributions
BearBot includes images sourced from external creators. Below are the proper attributions:

- **User Profile Image:** [Pinterest](https://in.pinterest.com/pin/761178774506401910/)
- **BearBot Avatar:** [Google Images](https://images.app.goo.gl/QeC8tGJqBjA7c89s6)
- **Background Image:** [Pinterest](https://pin.it/40vXE2KeK)  