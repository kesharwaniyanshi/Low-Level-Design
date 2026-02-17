package FileSystem;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

public abstract class FileSystemNode {
    private String name;
    private Map<String, FileSystemNode> children;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public FileSystemNode(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        children = new java.util.HashMap<>();
    }

    public void addChild(String name, FileSystemNode child) {
        children.put(name, child);
        modifiedAt = LocalDateTime.now();
    }

    public boolean hasChild(String name) {
        return this.children.containsKey(name);
    }
    public FileSystemNode getChild(String name) {
        return this.children.get(name);
    }
    // Remove child node
  public boolean removeChild(String name) {
    if (hasChild(name)) {
      children.remove(name);
      return true;
    }
    return false;
  }

  // Abstract methods for node operations
  public abstract boolean isFile();
  public abstract void display(int depth);

  // Getters and Setters
  public String getName() {
    return name;
  }

  public Collection<FileSystemNode> getChildren() {
    return children.values();
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getModifiedAt() {
    return modifiedAt;
  }

  // Update the modification timestamp
  protected void updateModifiedTime() {
    this.modifiedAt = LocalDateTime.now();
  }
}
