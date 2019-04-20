<!DOCTYPE html>
<html>

<head>
    <title>Assignments</title>
    <link rel="stylesheet" type="text/css" href="add.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container" id="curform">
        <h1>Automated Assignment Marker</h1>
        <form method="POST" action="process-add.php" class="form-group">
            <label>Max Grade</label>
            <br>
            <input type="text" class="form-control" name="grade">
            <br>
            <label>Input</label>
            <br>
            <input type="text" class="form-control" name="input" required>
            <br>
            <label>Output</label>
            <br>
            <textarea name="output"  required></textarea>
            <br>
            <br>
            <label>Check for</label>
            <br>
            <input type="checkbox" name="if" id="if">IF
            <input type="checkbox" name="if_else" id="if_else" value="if_else">If else
            <input type="checkbox" name="else_if" id="else_if" value="else_if">Else if
            <input type="checkbox" name="switch" id="switch" value="switch">Switch
            <input type="checkbox" name="for_loop" id="for_loop" value="for_loop">For loop
            <input type="checkbox" name="while" id="while" value="while">While
            <br>
            <br>
            <label>Compiling</label>
            <br>
            <input type="text" class="form-control" name="comp" placeholder="precentage %" required>
            <br>
            <label>Documention Comments</label>
            <br>
            <input type="text" class="form-control" name="dom" placeholder="precentage %" required>
            <br>
            <label>Code Comments</label>
            <br>
            <input type="text" class="form-control" name="code" placeholder="precentage %" required>
            <br>
            <label>Statements</label>
            <br>
            <input type="text" class="form-control" name="stat" placeholder="precentage %" required>
            <br>
            <br>
            <input type="file" name="jfile" id="jfile" required>
            <br>
            <br>
            <input type="submit" class="btn btn-primary" name="submit" value="submit">
        </form>
    </div>
</body>

</html>
