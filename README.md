# 플러그인 공지
`등록된 공지가 없습니다.`

<br>

# 플러그인 소개
해당 플러그인은 `스크립트 플러그인`의 애드온입니다.<br>
아이템에 `다양한 값 또는 변수를 추가하고 불러올 수 있는 애드온`입니다.<br>
주로 간단한 시스템의 데이터 값을 불러오는데 사용됩니다.

<br>

# 플러그인 저작권
해당 플러그인의 저작권은 `lone64`에게 있습니다.<br>
플러그인 소스코드를 사용하고자 한다면, 출처 또는 개발자 이름을 남겨주세요!

<br>

# 플러그인 버전
해당 플러그인이 호환하는 버전은 `1.19 ~ 1.20.1`입니다.

<br>

# 플러그인 설정파일
`해당 플러그인의 설정파일은 존재하지 않습니다.`

# 스크립트 예제코드
```skript
# 들고 있는 아이템에 arg-1의 이름인 NBT를 arg-1의 값으로 추가하는 명령어
command /addnbt <string> <string>:
	trigger:
		if arg 1 is not set:
			message "&c값을 입력하여 주세요!" to player
		else if arg 2 is not set:
			message "&c값을 입력하여 주세요!" to player
		else:
			if player's tool is air:
				message "&cNBT를 추가할 아이템을 들고 있어주세요!" to player
				stop
			else if nbt named arg-1 of player's tool is true:
				message "&c이미 추가된 NBT입니다!" to player
				stop
			set {_item} to nbt named arg-1 in arg-2 of player's tool
			set player's tool to {_item}
			message "&a성공적으로 NBT를 추가하셨습니다!" to player

# 들고 있는 아이템에 arg-1의 이름인 NBT가 추가되어 있다면 그 값을 불러오는 명령어
command /nbtload <string>:
	trigger:
		if arg 1 is not set:
			message "&c값을 입력하여 주세요!" to player
		else:
			if player's tool is air:
				message "&cNBT를 추가할 아이템을 들고 있어주세요!" to player
				stop

			set {_amount} to load nbt named arg-1 of player's tool
			if {_amount} is not set:
				message "&c저장된 값이 존재하지 않습니다!" to player
				stop
			message "&f이름: %arg-1% &7/ &f값: %{_amount}%" to player

# 들고 있는 아이템에 arg-1의 이름인 NBT가 추가되어 있는가를 판단하는 명령어
command /isnbt <string>:
	trigger:
		if arg 1 is not set:
			message "&c값을 입력하여 주세요!" to player
		else:
			if player's tool is air:
				message "&cNBT를 추가할 아이템을 들고 있어주세요!" to player
				stop
			else if nbt named arg-1 of player's tool is true:
				message "&aNBT가 추가되어 있습니다!" to player
				stop
			message "&cNBT가 추가되어 있지 않습니다!" to player
```
