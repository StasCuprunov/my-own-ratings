import {FunctionComponent, useEffect, useMemo, useState} from "react";
import {RatingEntryFormDialogTemplate} from "./RatingEntryFormDialogTemplate";
import {RangeOfValues} from "../../../../../model/RangeOfValues";
import {InputValidation} from "../../../../../model/InputValidation";
import {handleChange, handleChangeWithValue} from "../../../../../utility/FormUtility";
import {
    createRatingEntry,
    editRatingEntry,
    getInputValueProps,
    getLabelNameProps,
    getLabelValueProps,
    hasAlreadyRatingEntryWithName
} from "./RatingEntryFormDialogFunctions";
import {getInputNameProps} from "../../../../form/RatingFormFunctions";
import {useNavigate} from "react-router-dom";
import {WEBSITE_ROUTING_REFRESH} from "../../../../../constant/routing/WebsiteRoutingConstants";
import {RatingEntry} from "../../../../../model/rating-entry/RatingEntry";
import {isAllowedScaleValue} from "../../../../RatingUtility";

const labelName: any = getLabelNameProps();
const labelValue: any = getLabelValueProps();

export const RatingEntryFormDialog: FunctionComponent<any> = ({props}) => {
    const attributeValue: string = "value";
    const isEdit: boolean = props.isEdit;
    const rangeOfValues: RangeOfValues = props.rangeOfValues;
    const defaultRatingEntry: RatingEntry = props.ratingEntry;

    const navigate = useNavigate();

    const [ratingEntry, setRatingEntry] = useState(defaultRatingEntry);

    const [nameValidation, setNameValidation] =
        useState(new InputValidation());
    const [valueValidation, setValueValidation] =
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

        setValueValidation({
            ...valueValidation,
            condition: false
        });
    };

    const handleRatingEntryChange = (field: string, value?: any) => {
        if (value) {
            handleChangeWithValue(field, value, setRatingEntry);
        }
        return handleChange(field, setRatingEntry);
    };

    const handleValueChange = (value: number) => {
        return handleRatingEntryChange(attributeValue, value);
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

    const handleValueBlur = () => {
        let value: number = ratingEntry.value;
        let condition: boolean = false;
        let text: string = "";

        if (!isAllowedScaleValue(value, rangeOfValues.minimum, rangeOfValues.stepWidth)) {
            condition = true;
            text = "Value is not in scale."
        }

        setValueValidation({
            condition: condition,
            text: text
        });
    };

    const handleSubmit = async (event: any) => {
        event.preventDefault();

        if (nameValidation.condition || valueValidation.condition) {
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
        text: props.openButtonText,
        onClick: handleOpenDialogButtonOnClick,
        icon: props.icon
    };

    const inputName: any = useMemo(() =>
        getInputNameProps(ratingEntry.name, props.maximumLengthOfName, handleRatingEntryChange("name"),
            handleNameBlur), [ratingEntry.name]);
    const inputValue: any = useMemo(() =>
        getInputValueProps(rangeOfValues.stepWidth, rangeOfValues.minimum, rangeOfValues.maximum, ratingEntry.value,
            handleValueChange, handleValueBlur), [ratingEntry]);

    const formForName: any = {
        label: labelName,
        input: inputName,
        inputError: nameValidation
    };

    const formForValue: any = {
        label: labelValue,
        inputNumber: inputValue,
        inputError: valueValidation
    };

    const ratingEntryFormDialogProps: any = {
        title: props.title,
        oldName: (isEdit) ? defaultRatingEntry.name : null,
        submitButton: {
            ...props.submitButton,
            icon: props.icon
        },
        openDialogButton: (isEdit) ? null : openDialogButton,
        isOpen: props.isOpen,
        handleClose: handleClose,
        handleSubmit: handleSubmit,
        formForName: formForName,
        formForValue: formForValue
    };

    return (
        <RatingEntryFormDialogTemplate props={ratingEntryFormDialogProps}/>
    );
};