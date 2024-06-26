import {FunctionComponent} from "react";
import {DeleteRatingDialogTemplate} from "./DeleteRatingDialogTemplate";
import {deleteRating} from "../../RatingFunctions";
import {WEBSITE_ROUTING_INDEX} from "../../../../constant/routing/WebsiteRoutingConstants";
import {useNavigate} from "react-router-dom";
import {getDeleteButtonInDialogProps, getDeleteButtonProps} from "../../../../utility/FormUtility";

export const DeleteRatingDialog: FunctionComponent<any> = ({props}) => {
    const setIsOpen = props.setIsOpen;
    const navigate = useNavigate();

    const handleOpenDialog = (): void => {
        setIsOpen(true);
    };

    const handleClose = (): void => {
        setIsOpen(false);
    };

    const handleDelete = async () => {
        const {error} = await deleteRating(props.ratingId);

        if (error) {
            props.setBackendError(error);
            return;
        }
        navigate(WEBSITE_ROUTING_INDEX);
    };

    const deleteDialogButton: any = getDeleteButtonInDialogProps(handleDelete);
    const deleteOpenButton: any = getDeleteButtonProps("rating", handleOpenDialog);

    const templateProps: any = {
        name: props.name,
        isOpen: props.isOpen,
        handleClose: handleClose,
        deleteOpenButton: deleteOpenButton,
        deleteDialogButton: deleteDialogButton
    };

    return (
        <DeleteRatingDialogTemplate props={templateProps}/>
    );
};