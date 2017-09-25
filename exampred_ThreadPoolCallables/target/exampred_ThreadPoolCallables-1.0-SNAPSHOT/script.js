var getGroups = function () {
    var promise = fetch("http://localhost:8080/exampred_ThreadPoolCallables/api/group");
    promise.then(function (response) {
        tblHeaders = "<th>Group Members</th><th>Class</th><th>Group Number</th>";
        return response.json();
    }).then(function (groups) {
        var mappedGroups = groups.map(function (group) {
            return "<tr><td>" + group.group + "</td><td>"
                    + group.className + "</td><td>"
                    + group.number + "</td></tr>";
        }).join("");
        document.getElementById("tblbody").innerHTML = mappedGroups;
        document.getElementById("tblhead").innerHTML = tblHeaders;
    });
};

getGroups();