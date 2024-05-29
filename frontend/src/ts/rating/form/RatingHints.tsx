export const getHintRequired = () => {
    return createHint("1", "Required");
};

export const getHintRatingNameMustBeUnique = () => {
    return createHint("2", "Has to be unique for every rating");
};

export const getHintWhatIsAScale = () => {
    let text: string = "A scale has a minimum, maximum and other values which are in the scale. Every value excludes " +
        "the minimum and maximum has a previous and following value with the same distance to each other which is " +
        "described by the step width."
    return createHint("3", text);
};

export const getHintMaximumDecimalPlaces = (maximumNumberOfDecimalDigits: number) => {
    const getText = (maximumNumberOfDecimalDigits: number): string => {
        return "Every attribute may have maximum " + maximumNumberOfDecimalDigits + " decimal places."
    };
    return createHint("4", getText(maximumNumberOfDecimalDigits));
};

export const getHintAdaptionRatingEntries = () => {
    let text: string = "If you adapt the scale then you have to check if every rating entry is in the new scale. " +
        "If not then the new scale is invalid and you have to adapt or delete the relevant rating entries."
    return createHint("5", text);
};

const createHint = (hintSymbol: string, text: string) => {
    return (
        <li>
            <sup>{hintSymbol}</sup>{text}
        </li>
    );
};