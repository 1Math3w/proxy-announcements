# ProxyAnnouncements

Simple announcements that can be sent across all of your servers within the proxy

## Features

- Scheduled announcements to repeat every X seconds
- Force announcements using /announce <announcement-name>
- Customizable announcement action bar
- Customizable announcement title
- Filter servers that announcements will be sent to
- HEX colors

## Commands

- `/announce <announcement-name>` - Force sends specified announcement | permission: `bungeecord.command.announce`
- `/proxyannouncements reload` - Reloads configuration | permission: `bungeecord.command.proxyannouncements`
- `/proxyannouncements unload` - Unloads scheduled announcements | permission: `bungeecord.command.proxyannouncements`
- `/proxyannouncements load` - Loads scheduled announcements | permission: `bungeecord.command.proxyannouncements`

## Configurations

**config.yml**

```yml
# If true announcements will be sent in a random order
random: false
# Seconds between each announcement
interval: 60
servers:
  - "*"
```

**announcements.yml**

```yml
test1:
  # Content of announcement
  lines:
    - "#FFFF00Proxy Announcement 1"
test2:
  # Content of announcement
  lines:
    - "&7Proxy Announcement 2"
  # Announcement content that will be displayed in action bar. Remove if no actionbar message should be sent
  actionbar: "&7Announcement 2 ActionBar"
  # Announcement title. Remove if no title message should be sent
  title:
    title: "&7Announcement 2 Title"
    subtitle: "&7Announcement 2 Subtitle"
    duration: 30 # Amount is specified in ticks
    fadein: 5 # Amount is specified in ticks
    fadeout: 5 # Amount is specified in ticks
```
