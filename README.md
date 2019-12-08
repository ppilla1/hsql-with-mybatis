# hsql-with-mybatis
HSQL/MyBatis/SpringBoot  with multiple Datasources

FSM (Finite State Machine) represents state transitions (logic) and final (action) on each transition. 
It should be implemented such that there should be separation between state transitions (logic) and (action)
on transition completion

FSM (logic) can be represented in tabular format by following below format
Given <We are in some state> When <we get some event> Then <we change to next state> And <Invoke Action>.

Above template can be used to represet complete set of state transitions in the form of table
E.g state transition table for Subway turnstile door
---------------------------------------------------
|Give State   | Event | Next State   | Action     |
---------------------------------------------------
|Locked       | Coin  | UnLocked       | unLock()   |
---------------------------------------------------
|Locked       | Pass  | Locked       | alarm()    |
---------------------------------------------------
|UnLocked     | Coin  | UnLocked     | thankYou() |
---------------------------------------------------
|UnLocked     | Pass  | Locked       | lock()     |
---------------------------------------------------

