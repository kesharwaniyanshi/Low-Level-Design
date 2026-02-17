package FileSystem;

public class FileSystemManager {
    private static FileSystemManager instance;
    private FileSystemNode root;

    private FileSystemManager() {
        this.root = new Directory("/");
    }
    
    public static synchronized FileSystemManager getInstance() {
        if (instance == null) {
            instance = new FileSystemManager();
        }
        return instance;
    }

    // path = "/document/cwa_lld/design_file_system"
    // Checking if path is not null, not empty, and starts with
    // Validate file path to be non-empty and properly formatted
    public boolean isValidFilePath(String path) {
        // Returns true because path meets all criteria
        return path != null && !path.isEmpty() && path.startsWith("/");
    }

    public boolean createPath(String path) {
        if (!isValidFilePath(path)) {
            System.out.println("Invalid path: " + path);
            return false;
        }
        String[] parts = path.split("/");
        FileSystemNode current = root;

        for (int i = 1; i < parts.length - 1; i++) {
            String component = parts[i];
            if (component.isEmpty()) {
                continue;
            }
            if (!current.hasChild(component)) {
                FileSystemNode newDir = new Directory(component);
                current.addChild(component, newDir);
            }

            FileSystemNode child = current.getChild(component);
            if (child.isFile()) {
                return false;
            }
            current = child;
        }
        String lastComponent = parts[parts.length - 1];
        if (lastComponent.isEmpty()) {
            return false;
        }
        if (current.hasChild(lastComponent)) {
            return false;
        }

        FileSystemNode newNode;
        if (lastComponent.contains(".")) {
            newNode = new File(lastComponent);
        } else {
            newNode = new Directory(lastComponent);
        }

        current.addChild(lastComponent, newNode);
        return true;
    }

    // Helper method to get node at path
    // path = "/document/cwa_lld/design_file_system"
    private FileSystemNode getNode(String path) {
        // Check if path is valid
        // Path is valid, so continue
        if (!isValidFilePath(path))
            return null;
        // For root path
        // Path is not "/", so skip this
        if (path.equals("/"))
            return root;
        // Split path into components
        // pathComponents = ["", "document", "cwa_lld", "design_file_system"]
        String[] pathComponents = path.split("/");
        // Start from root
        // current = root directory "/"
        FileSystemNode current = root;
        // Traverse through the path
        // We need to process: "document", "cwa_lld", and "design_file_system"
        for (int i = 1; i < pathComponents.length; i++) {
            String component = pathComponents[i];
            if (component.isEmpty())
                continue; // Skip empty components
            // First iteration: component = "document"
            // Second iteration: component = "cwa_lld"
            // Third iteration: component = "design_file_system
            if (!current.hasChild(component)) {
                // If any component doesn't exist at its level, return null
                return null;
            }
            // Move to the next level
            // First iteration: current = "document" directory
            // Second iteration: current = "cwa_lld" directory
            // Third iteration: current = "design_file_system" directory
            current = current.getChild(component);
        }
        // Return the node found at the path
        // Returns the "design_file_system" directory node
        return current;
    }

    public boolean deletePath(String path) {
        if (!isValidFilePath(path)) {
            System.out.println("Invalid path: " + path);
            return false;
        }

        if (path.equals("/")) {
            System.out.println("Cannot delete root directory");
            return false;
        }

        String parentPath = getParentPath(path);
        FileSystemNode parent = getNode(parentPath);

        if (parent == null || parent.isFile()) {
            System.out.println("Parent path does not exist or is a file: " + parentPath);
            return false;
        }

        String lastComponent = path.substring(path.lastIndexOf("/") + 1);
        if (!parent.hasChild(lastComponent)) {
            System.out.println("Path does not exist: " + path);
            return false;
        }
        return parent.removeChild(lastComponent);

    }

    private String getParentPath(String path) {
        int lastSlashIndex = path.lastIndexOf("/");
        if (lastSlashIndex > 0) {
            return path.substring(0, lastSlashIndex);
        }
        return "/";
    }

    public boolean setFileContent(String path, String content) {
        FileSystemNode node = getNode(path);
        if (node != null && node.isFile()) {
            File file = (File) node;
            (file).setContent(content);
            return true;
        }
        return false;
    }

    public String getFileContent(String path) {
        FileSystemNode node = getNode(path);
        if (node != null && node.isFile()) {
            File file = (File) node;
            return file.getContent();
        }
        return null;
    }

    public void display() {
        root.display(0);
    }

}
