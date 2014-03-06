#Zerotype
--------
Zerotype is a simple java implementation for agent-based modelling of a dengue-related infection and simulates the effect of ~~4~~2 serotypes. Conceptual model used for the project is SIR(Suceptible-Infected-~~Recovered~~Resistant) model. Zerotype augments diverse effects when infection hits the Philippines. As of the writing, Cebu is in focus. Project is under developement. We, the developers, are willingly accepting suggestions, recommendations, requests for the betterment of the project.

## Agent
========
The basic unit of population. An agent has properties and behavior and knows it's **environment**. An agent's behavior is dependent with the environment and could also be influenced by its neighbors. Random sample would be a work-around for non-spatial simulation.

##Environment
=============
The environment has it's own behavior too. It is capable of altering the population through its properties. It's properties are refered to as **preferences**.

##Preferences
=============
This is responsible for the **environment**'s properties. The preference could be altered through the __preferences.xml__. The important properties include:

  * **population-size** The total population count.
  *  ~~**infection-rate**~~ Rather the infection probability (subject for change for next increment).
  *  **initial-infected** Sets the number of initially infected agents.
  *  ~~**initial-recovered**~~ Sets the number of initially ~~recovered~~ resistant (subject for change for next increment).
  * **run-duration** Specify the simulation run duration.
  * **in-years** Specify if run duration be in years(true) or days(false).
  * **ser1-duration** The latency period in days if infected with serotype 1.
  * **ser2-duration** The latency period in days if infected with serotype 2.
  * (__alpha1-duration__) The period in days before a person gets infected with serotype 1.
  * (__alpha2-duration__) The period in days before a person gets infected with serotype 2.
  * (__beta1-duration__) The period in days before a person gets resistant with serotype 1.
  * (__beta2-duration__) The period in days before a person gets resistant with serotype 2.
  * **neighbors** The number of neighbors each agent could have.
  * **out-daily** (Not implemented yet.)
  * **out-yearly** (Not implemented yet.)
