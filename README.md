# Android Calculator

Dynamic Android Mobile application that functions as a calculator.

## Functionality

The following **required** functionality is completed:

* [x] **Number Input**: User can enter a number using the on-screen buttons.
* [x] **Operations Handling**: After a number is entered, an operation can be selected (e.g., `+`, `-`, `x`, `/`). The TextView displays the current number until the next number is entered.
* [x] **Continuous Input**: User can enter multiple numbers, and the top TextView is updated accordingly.
* [x] **Equals Operation**: When `=` is pressed, the result of the calculation is displayed, and additional operations can be performed.
* [x] **Chained Operations**: If an operation is pressed consecutively without `=`, the result of the current operation is displayed until another number is entered.
* [x] **Clear Functionality**: The `C` button clears all input, allowing a fresh start.
* [x] **Plus/Minus Toggle**: The `+/-` button toggles the sign of the current number between positive and negative.
* [x] **Percentage Calculation**: The `%` button divides the current number by 100 and displays the result.

## Trigonometric and Logarithmic Functions

In **landscape mode**, additional advanced mathematical operations are available:

* **Trigonometric Functions**:
    * **`sin`**: Calculates the sine of an angle (in degrees).
    * **`cos`**: Calculates the cosine of an angle (in degrees).
    * **`tan`**: Calculates the tangent of an angle (in degrees).

* **Logarithmic Functions**:
    * **`log`**: Calculates logarithm of a number.
    * **`ln`**: Calculates the natural logarithm .

  These functions enable users to perform logarithmic calculations for scientific purposes. For example, `log(10)` returns `1.0`, and `ln(2.718)` (approximate `e`) returns `1.0`.

## Extensions

The following **optional** enhancements have been implemented or are planned:

* [ ] **Logging Button Clicks**: Log every button press for analytical or debugging purposes.
* [x] **Landscape Mode Layout Change**: Provide a different layout when the device is in landscape orientation.
    * [x] **Additional Math Functions**: Implemented trigonometric (`sin`, `cos`, `tan`) and logarithmic (`log`, `ln`) functions for advanced calculations.

## Video Walkthrough

Here's a walkthrough of implemented user stories:


## Notes

During development, I encountered the following challenges:

1. **Layout Conflicts**: Adjusting the button layout using `ConstraintLayout` to ensure proper proportions and alignment for both portrait and landscape orientations.
2. **Operation Handling**: Managing the calculator logic to allow for consecutive operations without needing to press `=` between them.
3. **State Persistence**: Ensuring the calculator state is maintained during screen rotations or when the app is minimized and reopened.
4. **Trigonometric & Logarithmic Calculations**: Converting angle input to degrees for trigonometric calculations and ensuring logarithmic functions return accurate results.

## License

    Copyright [2024] [Weidi Zheng]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
