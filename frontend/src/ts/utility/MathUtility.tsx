export const isLastIndex = (index: number, length: number): boolean => {
    return index === (length - 1);
};

export const getSmallestPositiveNumberWithNumberOfDecimalDigits = (numberOfDecimalDigits: number): number => {
    return 1.0 / (Math.pow(10, numberOfDecimalDigits));
};