import {FunctionComponent, useEffect, useMemo, useState} from "react";
import {RatingEntryFormDialogTemplate} from "./RatingEntryFormDialogTemplate";
import {RangeOfValues} from "../../../../../model/RangeOfValues";
import {InputValidation} from "../../../../../model/InputValidation";
import {handleChange} from "../../../../../utility/FormUtility";
import {
    createRatingEntry,
    editRatingEntry,
    getInputValueProps,
    hasAlreadyRatingEntryWithName
} from "./RatingEntryFormDialogFunctions";
import {getInputNameProps} from "../../../../form/RatingFormFunctions";
import {useNavigate} from "react-router-dom";
import {WEBSITE_ROUTING_REFRESH} from "../../../../../constant/routing/WebsiteRoutingConstants";
import {RatingEntry} from "../../../../../model/RatingEntry";

export const RatingEntryFormDialog: FunctionComponent<any> = ({props}) => {
    const isEdit: boolean = props.isEdit;
    const rangeOfValues: RangeOfValues = props.rangeOfValues;
    const defaultRatingEntry: RatingEntry = props.ratingEntry;

    const navigate = useNavigate();

    const [ratingEntry, setRatingEntry] = useState(defaultRatingEntry);

    const [nameValidation, setNameValidation] =
        useState(new InputValidation());

    useEffect(() => {
        setRatingEntry(defaultRatingEntry);
    }, [defaultRatingEntry]);

    const handleOpenDialogButtonOnClick = () => {
        props.setIsOpen(true);
    };

    const handleClose = () => {
        props.setIsOpen(false);

        setRatingEntry(defaultRatingEntry);

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
        else if (hasAlreadyRatingEntryWithName(name, props.ratingEntries, ratingEntry.id)) {
            condition = true;
            text = "The name is already used by another rating entry.";
        }
        setNameValidation({
            condition: condition,
            text: text
        });
    };

    const handleSubmit = async (event: any) => {
        event.preventDefault();

        if (nameValidation.condition) {
            return;
        }

        const {error} = (isEdit) ? await editRatingEntry(ratingEntry) : await createRatingEntry(ratingEntry);

        if (error) {
            props.setBackendError(error);
            return;
        }
        navigate(WEBSITE_ROUTING_REFRESH);
    };

    const openDialogButton: any = {
        type: "button",
        text: props.submitButtonText,
        onClick: handleOpenDialogButtonOnClick
    };

    const inputName: any = useMemo(() =>
        getInputNameProps(ratingEntry.name, props.maximumLengthOfName, handleRatingEntryChange("name"),
            handleNameBlur), [ratingEntry.name]);
    const inputValue: any = useMemo(() =>
        getInputValueProps(rangeOfValues.minimum, rangeOfValues.maximum, rangeOfValues.stepWidth, ratingEntry.value,
            handleRatingEntryChange("value")), [ratingEntry.value]);

    const ratingEntryFormDialogProps: any = {
        title: props.title,
        submitButtonText: props.submitButtonText,
        openDialogButton: (isEdit) ? null : openDialogButton,
        isOpen: props.isOpen,
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