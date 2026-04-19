import axios from "axios";

const API = axios.create({
  baseURL: "https://group-management-app-2.onrender.com"
});

export const updateGroup = (id, data) => API.put(`/groups/${id}`, data);
export const deleteGroup = (id) => API.delete(`/groups/${id}`);
export const getGroups = () => API.get("/groups");
export const createGroup = (data) => API.post("/groups", data);