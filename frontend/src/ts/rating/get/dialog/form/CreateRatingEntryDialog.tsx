import {FunctionComponent, useMemo, useState} from "react";
import {RatingEntryFormDialogTemplate} from "./RatingEntryFormDialogTemplate";
import {RatingEntry} from "../../../../model/RatingEntry";
import {handleChange} from "../../../../utility/FormUtility";
import {InputValidation} from "../../../../model/InputValidation";
import {getInputNameProps} from "../../../form/RatingFormFunctions";
import {getInputValueProps, hasAlreadyRatingEntryWithName} from "./RatingEntryFormDialogFunctions";
import {RangeOfValues} from "../../../../model/RangeOfValues";

export const CreateRatingEntryDialog: FunctionComponent<any> = ({props}) => {
    const rangeOfValues: RangeOfValues = props.rangeOfValues;

    const initializeRatingEntry = (): RatingEntry => {
        return new RatingEntry(undefined, props.ratingId);
    };

    const [ratingEntry, setRatingEntry] =
        useState(initializeRatingEntry());

    const [nameValidation, setNameValidation] =
        useState(new InputValidation());

    const [isOpen, setIsOpen] = useState(false);

    const handleOpenDialogButtonOnClick = () => {
        setIsOpen(true);
    };

    const handleClose = () => {
        setIsOpen(false);

        setRatingEntry(initializeRatingEntry());

        setNameValidation({
            ...nameValidation,
            condition: false
        });
    };

    const handleRatingEntryChange = (field: string) => {
        return handleChange(field, setRatingEntry);
    };

    const handleNameBlur = () => {
        let name: string = ratingEntry.name;
        let condition: boolean = false;
        let text: string = "";
        if (!name.trim()) {
            condition = true;
            text = "The name may not be empty.";
        }
        else if (hasAlreadyRatingEntryWithName(name, props.ratingEntries)) {
            condition = true;
            text = "The name is already used by another rating entry.";
        }
        setNameValidation({
            condition: condition,
            text: text
        });
    };

    const handleSubmit = (event: any) => {
        event.preventDefault();
    };

    const openDialogButton: any = {
        type: "button",
        text: "Create",
        onClick: handleOpenDialogButtonOnClick
    };

    const inputName: any = useMemo(() =>
            getInputNameProps(ratingEntry.name, props.maximumLengthOfName, handleRatingEntryChange("name"),
                handleNameBlur), [ratingEntry.name]);
    const inputValue: any = useMemo(() =>
        getInputValueProps(rangeOfValues.minimum, rangeOfValues.maximum, rangeOfValues.stepWidth, ratingEntry.value,
            handleRatingEntryChange("value")), [ratingEntry.value]);

    const ratingEntryFormDialogProps: any = {
        title: "Create a new rating entry",
        submitButtonText: "Create",
        openDialogButton: openDialogButton,
        isOpen: isOpen,
        handleClose: handleClose,
        handleSubmit: handleSubmit,
        inputName: inputName,
        inputValue: inputValue,
        nameValidation: nameValidation
    };

    return (
        <RatingEntryFormDialogTemplate props={ratingEntryFormDialogProps}/>
    );
};