import React, { useEffect, useState } from "react";
import {
  getGroups,
  createGroup,
  deleteGroup,
  updateGroup
} from "../services/api";

function GroupList() {
  const [editId, setEditId] = useState(null);
  const [editName, setEditName] = useState("");
  const [groups, setGroups] = useState([]);
  const [name, setName] = useState("");

  const loadGroups = async () => {
    const res = await getGroups();
    setGroups(res.data);
  };

  const handleAdd = async () => {
    if (!name) return;
    await createGroup({ groupName: name });
    setName("");
    loadGroups();
  };

  const handleDelete = async (id) => {
    await deleteGroup(id);
    loadGroups();
  };

  

  const handleUpdate = async (id) => {
    await updateGroup(id, { groupName: editName });
    setEditId(null);
    setEditName("");
    loadGroups();
  };

  useEffect(() => {
    loadGroups();
  }, []);

  return (
    <div className="container mt-4">

      <h2 className="text-center mb-4">Group Management</h2>

      <div className="card text-white bg-danger mb-3" style={{ width: "200px" }}>
        <div className="card-body">
          <h5 className="card-title">Total Groups</h5>
          <h3>{groups.length}</h3>
        </div>
      </div>

      <div className="mb-3">
        <input
          className="form-control d-inline w-50"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Enter group name"
        />
        <button className="btn btn-success ms-2" onClick={handleAdd}>
          Add Group
        </button>
      </div>

      <table className="table table-bordered">
        <thead className="table-light">
          <tr>
            <th>Sr.No</th>
            <th>Group Name</th>
            <th>Status</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
        </thead>

        <tbody>
          {groups.map((g, index) => (
            <tr key={g.groupId}>
              <td>{index + 1}</td>

              <td>
                {editId === g.groupId ? (
                  <input
                    value={editName}
                    onChange={(e) => setEditName(e.target.value)}
                  />
                ) : (
                  g.groupName
                )}
              </td>

              <td>{g.isActive ? "Active" : "Inactive"}</td>

              <td>
                {editId === g.groupId ? (
                  <button
                    className="btn btn-success btn-sm"
                    onClick={() => handleUpdate(g.groupId)}
                  >
                    Save
                  </button>
                ) : (
                  <button
                    className="btn btn-warning btn-sm"
                    onClick={() => {
                      setEditId(g.groupId);
                      setEditName(g.groupName);
                    }}
                  >
                    Edit
                  </button>
                )}
              </td>

              <td>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => handleDelete(g.groupId)}
                >
                  Delete
                </button>
              </td>

            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default GroupList;