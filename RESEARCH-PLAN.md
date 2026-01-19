# WAILA Component Access Research Plan

Since Hytale just launched and documentation is limited, we need to systematically figure out how to access the ECS components. Here's our research and implementation plan.

## 🎯 Primary Goal
Access the `CameraManager` component from a player to implement WAILA (What Am I Looking At) functionality.

## 🔍 What We Know
From the decompiled `CameraManager.class`:
```java
public class CameraManager implements Component<EntityStore> {
    public Vector3i getLastTargetBlock()  // ← This is what we need!
    public void setLastBlockPosition(Vector3i targetBlock)
    public Vector2d getLastScreenPoint()
    public MouseButtonState getMouseButtonState(MouseButtonType mouseButtonType)
    // ...
}
```

## 🧩 Missing Pieces
1. **PlayerRef Access**: How to get `PlayerRef` from `CommandContext`
2. **EntityStore Access**: How to get `EntityStore` from `PlayerRef`  
3. **Component Access**: How to get `CameraManager` from `EntityStore`

## 🔬 Research Methods

### Method 1: Command Context Investigation
Use `/waila-debug` command to:
- Examine `CommandContext` methods
- Find player access patterns
- Test reflection if needed

### Method 2: Decompiled Code Analysis
Search the decompiled server for:
- `EntityStore` class and methods
- `PlayerRef` class and methods
- Component access patterns

### Method 3: Event System Analysis
- Fix event registration syntax
- Use player events to access components
- Monitor player interactions

## 📋 Implementation Steps

### Step 1: Player Access Discovery ✅
- [x] Created debug command to investigate `CommandContext`
- [ ] Find method to get `PlayerRef` from command
- [ ] Test different player access approaches

### Step 2: ECS Component Access 🔄
- [x] Created `CameraQueryService` framework
- [ ] Implement `getCameraManagerComponent()` method
- [ ] Test component access patterns

### Step 3: Testing & Validation
- [ ] Test with actual players in-game
- [ ] Validate `getLastTargetBlock()` returns correct positions
- [ ] Handle edge cases (no target, invalid components)

### Step 4: Integration
- [ ] Create real-time monitoring system
- [ ] Implement UI display (NoesisGUI)
- [ ] Add block information lookup

## 🛠️ Available Tools

### Commands
- `/test` - Basic plugin test
- `/waila` - WAILA information and status
- `/waila-debug` - Debug component access

### Services
- `CameraQueryService` - Component access framework
- `CameraQueryService.BlockLookInfo` - Data structure for results

## 🎮 Testing Strategy

1. **Build and deploy** the plugin
2. **Run commands** to gather information about available APIs
3. **Use reflection** if necessary to discover methods
4. **Test with players** looking at blocks in-game
5. **Iterate** based on findings

## 🔧 Expected Code Pattern

Based on ECS patterns, the final implementation should look like:
```java
// Get player from command context (TO BE DISCOVERED)
PlayerRef playerRef = getPlayerFromContext(ctx);

// Access entity store (TO BE DISCOVERED)  
EntityStore entityStore = playerRef.getEntityStore();

// Get component (KNOWN PATTERN)
CameraManager camera = entityStore.getComponent(CameraManager.getComponentType());

// Query target block (KNOWN METHOD)
Vector3i target = camera.getLastTargetBlock();
```

## 📚 Documentation Gaps

Since Hytale just launched, we need to:
- Document our findings for the community
- Share working patterns with other modders
- Contribute to the growing knowledge base

## 🚀 Next Actions

1. **Test the debug command** in-game
2. **Examine command context** for player access
3. **Try different API approaches** until we find the right one
4. **Document successful patterns** for future reference

---

This is essentially reverse engineering the Hytale ECS system to implement WAILA. Since the game is so new, we're pioneering these patterns for the modding community!
