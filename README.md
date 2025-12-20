# A Jane Austen Adventure
[Video 1](https://www.loom.com/share/4c17fca96468472abd184cb10cd6f137), [Video 2](https://www.loom.com/share/bf315bcbfc2745de96839482740d1e59), [Video 3](https://www.loom.com/share/df49cb6cbfa54a58bb04582a7a93ba6d)

> [!NOTE]
> Loom has a 5-minute video limit, so these videos are just meant to be watched sequentially. Part code walkthrough and part learning reflection!

# A Christmas Thunderbird

As I spent more time with the Digital Logic class this term, I started thinking more about the Thunderbird Project. Here are some of the musings I had this term and some of the things I worked on.

## One FSM, Two FSMs, or One-and-a-Half FSM??
When I did this project last year, I almost at once defaulted to using a single FSM that is duplicated twice to control the left and right lights. The two bit input is (conditionally) split into two one-bit inputs: one bit selecting the direction and the other bit controlling whether the lights are on or off. The reason I chose not to include a hazard was because I could not figure out how to sync up the lights (both lights on the same state), and I personally think of the unsynced lights as inelegant.

This year, I realized the alternative of using a single FSM for both lights. That is, having each of the ten output states as an individual FSM state. This would technically work, but would be very clunky and inefficient to implement. It would also involve fairly complex state transition equations.

I then realized that the most elegant solution is to have a single Mealy FSM instead of either of these (hypothetically Moore) FSMs. This solution would have a single FSM with four states: OFF, L1, L2, L3. The input would be two-bit, and the effective FSM input would be an OR of the two bits. That is, the FSM would behave like the FSM in the first (two FSM) solution with the input being an OR of the two bits.

```verilog
NS[0] = ~CS[0] & (in[0] | in[1] | CS[1]);
NS[1] = (CS[1] & ~CS[0]) | (~CS[1] & CS[0]);
```

The outputs can then be assigned based on the specific input.

```verilog
assign la = (CS[0] | CS[1]) & in[0];
assign lb = CS[1] & in[0];
assign lc = CS[0] & CS[1] & in[0];
assign ra = CS[0] & CS[1] & in[1];
assign rb = CS[1] & in[1];
assign rc = (CS[0] | CS[1]) & in[1];
```

The downside to this is that changes in the input will produce immediate changes in the lights. My initial solution, and the other two solutions here, will let the cycle finish before it reflects changes in the input. That is, the lights will not switch to OFF or Hazard or etc until the current cycle finishes. In this solution, flicking the switch OFF mid-cycle will turn the lights off, and so will changing directions. Switching from left to right, for example, will switch to the right lights mid-cycle, meaning that the right lights start in the second state. This is a result of having the states separate from the outputs. The FSM in this solution does not directly control the lights - it cycles through four states, and the lights are directly dependent on those states, but also depend on other factors (direction input). I believe this is also representative of the larger differences between Moore and Mealy machines.

## The Digibird
I knew at the start that playing Jingle Bells would require sending PWM signals to a passive buzzer. I was unsure of how to implement the multiple counters required using only structural Verilog. I tried for a while, but was unsuccessful. Luckily, Luc and Jessica discovered Behavioral Verilog on their own, and came to me with a rudimentary script that worked. I helped them trim, polish, and improve it. The script has two main counters. One to generate the frequency and one to move on to the next note. In order to have rests between the notes, the counter first turns the speaker off and then resets to proceed on to the next note. I do hope to try building a structural counter in the future to better understand the differences between structural and behavioral verilog. I believe it will involve using adder modules. I feel like I am becoming much more thorough in terms of my understanding of Digital Logic concepts the second time through, and would like to continue that throughout the year in addition to Data Structures.

### Code (Last Year)
https://github.com/Zo-Bro-23/diglog/blob/fda3fd09c96a3a761be4820922697fb9630bcfa5/thunderbird/zbird.v#L1-L64