package FileSystem;

public class Directory extends FileSystemNode {

    public Directory(String name) {
        super(name);
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public void display(int depth) {
        // Example: For a directory at path "/document/cwa_lld" at depth 2
        // indent = " " (4 spaces: depth 2 * 2 spaces per depth)
        // Output would be: " 📁 cwa_lld"
        // For our example, if depth is 2 (meaning this directory is at the 2nd level)
        // Generate indent string of 4 spaces (2*2)
        String indent = " ".repeat(depth * 2);
        // Print the directory with appropriate indentation and emoji
        // e.g., " 📁 cwa_lld"
        System.out.println(indent + "📁 " + getName());
        // Recursively display children nodes with increased depth
        for (FileSystemNode child : getChildren()) {
            child.display(depth + 1);
        }
    }
}
