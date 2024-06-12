const exactMath = require("exact-math");
export const isAllowedScaleValue = (value: number, minimum: number, stepWidth: number): boolean => {
    return Number.isInteger(
        exactMath.div(
            exactMath.sub(value, minimum),
            stepWidth)
    );
};