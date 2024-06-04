import {FunctionComponent, useEffect, useState} from "react";
import {RatingPage} from "./RatingPage";
import {GridCellParams} from "@mui/x-data-grid";
import {RatingEntry} from "../../model/rating-entry/RatingEntry";
import {RatingEntryForDataGrid} from "../../model/rating-entry/RatingEntryForDataGrid";

export const Rating: FunctionComponent<any> = ({props}) => {
    const id: string = props.id;
    const ratingEntries: RatingEntry[] = props.ratingEntries;

    const [ratingEntriesForDataGrid, setRatingEntriesForDataGrid]
        = useState<RatingEntryForDataGrid[]>([]);

    const [isDeleteRatingDialogOpen, setIsDeleteRatingDialogOpen] =
        useState(false);
    const [isCreateRatingEntryDialogOpen, setIsCreateRatingEntryDialogOpen] =
        useState(false);
    const [isEditRatingEntryDialogOpen, setIsEditRatingEntryDialogOpen] =
        useState(false);
    const [isDeleteRatingEntryDialogOpen, setIsDeleteRatingEntryDialogOpen] =
        useState(false);

    const [backendError, setBackendError] = useState(null);

    const [ratingEntry, setRatingEntry] = useState(new RatingEntry());

    useEffect(() => {
        createDataEntries();
    }, []);

    const createDataEntries = (): void => {
        const length: number = ratingEntries.length;
        let dataGridEntries: RatingEntryForDataGrid[] = [];
        for (let index: number = 0; index < length; index++) {
            dataGridEntries.push(
                new RatingEntryForDataGrid(
                    ratingEntries[index],
                    handleEditRatingEntryButtonOpenDialogClick,
                    handleDeleteRatingEntryButtonOpenDialogClick
                )
            );
        }
        setRatingEntriesForDataGrid(dataGridEntries);
    };

    const handleEditRatingEntryButtonOpenDialogClick = (): void => {
        setIsEditRatingEntryDialogOpen(true);
    };

    const handleDeleteRatingEntryButtonOpenDialogClick = (): void => {
        setIsDeleteRatingEntryDialogOpen(true);
    }

    const handleOnCellClick = (params: GridCellParams) => {
        let field: string = params.field;
        if (field === "edit" || field === "delete") {
            setRatingEntry(params.row.ratingEntry);
        }
    };

    const dialogProps: any = {
        ratingId: id,
        setBackendError: setBackendError
    };

    const deleteRatingDialogProps: any = {
        ...dialogProps,
        isOpen: isDeleteRatingDialogOpen,
        setIsOpen: setIsDeleteRatingDialogOpen
    };

    const ratingEntryFormDialogProps: any = {
        ...dialogProps,
        rangeOfValues: props.rangeOfValues,
        ratingEntries: ratingEntries,
        maximumLengthOfName: props.maximumLengthOfName
    };

    const createRatingEntryDialogProps: any = {
        ...ratingEntryFormDialogProps,
        isOpen: isCreateRatingEntryDialogOpen,
        setIsOpen: setIsCreateRatingEntryDialogOpen
    };

    const editRatingEntryDialogProps: any = {
        ...ratingEntryFormDialogProps,
        ratingEntry: ratingEntry,
        isOpen: isEditRatingEntryDialogOpen,
        setIsOpen: setIsEditRatingEntryDialogOpen
    };

    const deleteRatingEntryDialogProps: any = {
        ...dialogProps,
        ratingEntry: ratingEntry,
        isOpen: isDeleteRatingEntryDialogOpen,
        setIsOpen: setIsDeleteRatingEntryDialogOpen
    };

    return (
        <RatingPage id={id} name={props.name} description={props.description}
                    rangeOfValues={props.rangeOfValues} ratingEntriesForDataGrid={ratingEntriesForDataGrid}
                    backendError={backendError} handleOnCellClick={handleOnCellClick}
                    deleteRatingDialogProps={deleteRatingDialogProps}
                    createRatingEntryDialogProps={createRatingEntryDialogProps}
                    editRatingEntryDialogProps={editRatingEntryDialogProps}
                    deleteRatingEntryDialogProps={deleteRatingEntryDialogProps}
        />
    );
};