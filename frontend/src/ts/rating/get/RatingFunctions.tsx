import {getApiRoutingRatingsFindById} from "../../constant/routing/APIRoutingConstants";
import {useGet} from "../../interface/useGet";
import {GridColDef} from "@mui/x-data-grid";
import {Button} from "../../component/atom/button/Button";

export const useRating = (id: string | undefined) => {
    return useGet(getApiRoutingRatingsFindById(id));
};

export const getDeleteRatingButtonObject = (id: string) => {
    return {
        type: "button",
        text: "Delete"
    };
};

export const getCreateRatingEntryButtonObject = (ratingId: string) => {
    return {
        type: "button",
        text: "Create"
    }
};

export const getColumns = (): GridColDef[] => {
    return [
        {
            field: 'name',
            headerName: 'Name',
            width: 400
        },
        {
            field: 'value',
            headerName: 'Value',
            width: 400
        },
        {
            field: 'edit',
            headerName: 'Edit',
            sortable: false,
            filterable: false,
            renderCell: editButton
        },
        {
            field: 'delete',
            headerName: 'Delete',
            sortable: false,
            filterable: false,
            renderCell: deleteButton
        }
    ];
};

const editButton = (params: any) => {
    return (
        <Button props={getEditButtonObject(params.row.id)}/>
    );
};

const getEditButtonObject = (id: string) => {
    return {
        type: "button",
        text: "Edit"
    };
};

const deleteButton = (params: any) => {
    return (
        <Button props={getDeleteButtonObject(params.row.id)}/>
    );
};

const getDeleteButtonObject = (id: string) => {
    return {
        type: "button",
        text: "Delete"
    };
};