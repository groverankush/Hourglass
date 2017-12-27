# Hourglass

[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/groverankush/Hourglass)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)



Android-Hourglass is a Countdown timer that provides the feature to pause and resume the timer. The module is loaded with convenience methods to get a way through timers.


<img src="https://github.com/groverankush/Hourglass/tree/master/media/hourglass_demo.gif" width="250"/>


## Getting Started

These are the all the instructions you need to use SuperLog in your apps.

### Compile

Just a simple debug `compile` will get you up and running with SuperLog.

```
compile 'com.ankushgrover:Hourglass:1.0.0'
```

Adding this line will enable you to use every feature `Hourglass` has.


### Usage

Just initialize an object of `Hourglass`. The class is loaded with a lot of convinience methods to handle various actions. Some of them are listed below:

* Three constructors for initializing
        * public Hourglass()       //default interval is 1 second
        * public Hourglass(time)   //default interval is 1 second 
        * public Hourglass(time, interval)
* Convineince methods to manage timer
        * `public void startTimer()`
        * `public void stopTimer()`
        * `public void pauseTimer()`
        * `public void resumeTimer()`
* Abstract methods for updating UI
        * `public void onTimerTick() `
        * `public void onTimerFinish() `
* Setters for setting time and interval
        * `public void setTime()`
        * `public void setInterval()`
        
        
### Examples

Here is an example:

```
        Hourglass hourglass = new Hourglass(50000, 1000) {
            @Override
            public void onTimerTick(long timeRemaining) {
                Log.d("Hourglass", String.valueOf(timeRemaining));
            }

            @Override
            public void onTimerFinish() {
                Log.d("Hourglass", "Timer finish.");
            }
        };

```

## Built With

* Android SDK
* [Maven](https://bintray.com/ankushgrover) - Dependency Management


## Author

[Ankush Grover](https://ankushgrover.com/)


## License

This project is licensed under MIT License, - see the [LICENSE.md](https://github.com/groverankush/Hourglass/blob/master/LICENSE) file for details

