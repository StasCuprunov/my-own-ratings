import {FunctionComponent} from "react";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

export const EditRatingPage: FunctionComponent<any> = ({oldName, oldDescription, handleSubmit}) => {
    return (
        <div>
            <h1>Edit the rating {oldName}</h1>
            <form onSubmit={handleSubmit}>
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>Attribute</TableCell>
                                <TableCell>Old value</TableCell>
                                <TableCell>New value</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            <TableRow>
                                <TableCell>LABEL Name</TableCell>
                                <TableCell>{oldName}</TableCell>
                                <TableCell>INPUT Name</TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell>LABEL Description</TableCell>
                                <TableCell>{oldDescription}</TableCell>
                                <TableCell>INPUT Description</TableCell>
                            </TableRow>
                        </TableBody>
                    </Table>
                </TableContainer>
            </form>
        </div>
    );
};