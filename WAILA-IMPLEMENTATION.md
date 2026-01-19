# WAILA (What Am I Looking At) for Hytale

This plugin demonstrates how to implement a "What Am I Looking At" system in Hytale using the ECS (Entity Component System) pattern and the CameraManager component.

## Overview

WAILA is a popular mod feature that shows information about blocks and entities that the player is currently looking at. In Hytale, this can be implemented by accessing the `CameraManager` component through the ECS system.

## Key Components

### CameraManager Component

The `CameraManager` class provides essential methods for tracking what the player is looking at:

```java
public class CameraManager implements Component<EntityStore> {
    // Key methods for WAILA implementation:
    public Vector3i getLastTargetBlock()           // Position of the block being looked at
    public void setLastBlockPosition(Vector3i targetBlock)  // Update target block
    public Vector2d getLastScreenPoint()           // Screen coordinates
    public MouseButtonState getMouseButtonState(MouseButtonType type)  // Mouse interactions
}
```

### Implementation Strategy

1. **Access the Player's CameraManager Component**
   ```java
   // Through ECS system:
   EntityStore entityStore = playerRef.getEntityStore();
   CameraManager cameraManager = entityStore.getComponent(CameraManager.getComponentType());
   ```

2. **Get Target Block Information**
   ```java
   Vector3i targetBlock = cameraManager.getLastTargetBlock();
   if (targetBlock != null) {
       // Query world data for block information
       String blockInfo = getBlockInfo(targetBlock);
   }
   ```

3. **Display Information to Player**
   - Use NoesisGUI for custom UI overlays
   - Send chat messages with block details
   - Show titles/subtitles with block information

## Current Implementation

### Commands

- `/waila` - Shows information about the WAILA system and demonstrates the concept

### Event Handling

The `PlayerEvents` class is set up to handle player tick events where WAILA logic would be implemented:

```java
public static void onPlayerTick() {
    // This is where WAILA logic would be implemented
    // 1. Get all online players
    // 2. For each player, access their CameraManager component  
    // 3. Check what block they're looking at
    // 4. Send UI updates with block information
}
```

## Next Steps for Full Implementation

### 1. Event Registration
Fix the event registration syntax to properly listen for player events:
```java
// In ExamplePlugin.setup()
this.getEventRegistry().registerGlobal(PlayerEvent.class, PlayerEvents::onPlayerTick);
```

### 2. ECS Component Access
Implement proper access to the CameraManager component:
```java
public static void handlePlayerCameraUpdate(PlayerRef playerRef) {
    EntityStore entityStore = playerRef.getEntityStore();
    CameraManager cameraManager = entityStore.getComponent(CameraManager.getComponentType());
    
    Vector3i targetBlock = cameraManager.getLastTargetBlock();
    if (targetBlock != null) {
        // Process block information
    }
}
```

### 3. World Data Queries
Implement methods to query block information from the world:
```java
private String getBlockInfo(Vector3i blockPos) {
    // Query the world for block information at the given position
    // 1. Get the world/chunk data
    // 2. Look up the block type at the position  
    // 3. Get block properties, name, etc.
    // 4. Format the information for display
    return "Block information";
}
```

### 4. UI Implementation
Create UI elements using NoesisGUI to display block information:
- Overlay showing block name and properties
- Tooltip following the cursor
- HUD element with detailed block information

## Technical Challenges

### ECS System Integration
Hytale uses an Entity Component System where components like `CameraManager` are attached to player entities. Accessing these requires:
1. Getting the player's EntityStore
2. Retrieving the specific component type
3. Accessing the component's methods

### Event System
The event registration system needs proper typing and method signatures that match the expected event handler format.

### UI Framework
Hytale is transitioning to NoesisGUI for UI. Creating custom overlays will require:
- XAML-based UI definitions
- Asset-driven UI components
- Integration with the game's rendering system

## Documentation References

According to the Hytale modding documentation:

- **ECS Pattern**: Hytale uses Entity Component System architecture
- **NoesisGUI**: The official UI framework (currently in transition)
- **Component Access**: Components are accessed through EntityStore
- **Player Events**: Events can be registered for player interactions

## Usage

1. Build and install the plugin
2. Use `/waila` command to see current implementation status
3. Extend the `PlayerEvents` class to add full WAILA functionality

## Future Enhancements

- Real-time block information display
- Entity information when looking at mobs/players
- Configurable UI positioning and styling
- Integration with block property systems
- Support for modded block information

---

This implementation provides the foundation for a full WAILA system in Hytale. The key is properly accessing the CameraManager component through the ECS system and integrating with Hytale's event and UI frameworks.
